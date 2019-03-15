pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'JAVA8'
    }
        
    parameters {
        booleanParam(name: "RELEASE",
                description: "Release Version.",
                defaultValue: false)
    }
    stages {
        stage ('Initialize') {
            steps {
                bat '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
                bat 'mvn -Dmaven.test.skip=false install surefire-report:report'
            }
            post {
                success {
                     junit 'target/surefire-reports/**/*.xml'
                     publishHTML([allowMissing: false,
                     alwaysLinkToLastBuild: false,
                     keepAll: true,
                     reportDir: 'target/site/',
                     reportFiles: 'surefire-report.html',
                     reportName: 'JUNit', reportTitles: ''])
                        }
                }
        }
        
        stage("Deploy") {          
            when {
                expression { env.BRANCH_NAME !=~ /release\/.+/ }
            }
            steps {
              configFileProvider([configFile(fileId: 'edd7831a-3a6e-440e-9f60-1ef03a166602', variable: 'MAVEN_SETTINGS')]) {
                    bat "mvn -s $MAVEN_SETTINGS -B deploy"
              }
            }
        }
        
        stage("Release") {      
              when {
                  expression { params.RELEASE }
              }
              steps {
                script {
                  if (env.BRANCH_NAME ==~ /release\/.+/) {                    
                    configFileProvider([configFile(fileId: 'edd7831a-3a6e-440e-9f60-1ef03a166602', variable: 'MAVEN_SETTINGS')]) {
                    bat "git checkout $env.BRANCH_NAME"
                    bat "mvn -s $MAVEN_SETTINGS -Djgitflow.pushRemote=true gitflow:release-finish"
                    bat "mvn -s $MAVEN_SETTINGS -B deploy"
                    }
                  }  
               }
            }
        }
   }
 }
