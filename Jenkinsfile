pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'JAVA8'
    }
    parameters {
        booleanParam(name: "RELEASE",
                description: "Build a release from current commit.",
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
        stage("Release") {
            when {
                expression { params.RELEASE }
            }
            steps {
                ansiColor("xterm") {
                    sh "mvn -B release:prepare"
                    sh "mvn -B release:perform"
                }
            }
        }
    }
}