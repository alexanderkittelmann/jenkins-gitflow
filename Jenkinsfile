pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'JAVA8'
    }
    
    environment {
        GITHUB     = credentials('56eee954-a690-492f-bf57-d333d1320db2')
    }
    
    parameters {
        booleanParam(name: "DEPLOY",
                description: "Deploy in Nexus.",
                defaultValue: false);
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
                expression { params.DEPLOY }
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
                    bat "mvn -s $MAVEN_SETTINGS -B -Djgitflow.fetchRemote=true -Djgitflow.pushRemote=true gitflow:release-finish"
                    bat "mvn -s $MAVEN_SETTINGS -B deploy"
                    }
                  }  
               }
            }
        }
   }
 }