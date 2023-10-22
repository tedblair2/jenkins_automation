pipeline {
    agent any
    tools {
        gradle 'gradle'
    }
    stages {
        stage('Build docker image') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/tedblair2/jenkins_automation']])
                script {
                    def imageExists = sh(script: 'docker images -q t3ddblair/ktor-jenkins:latest', returnStdout: true).trim()

                    if (imageExists) {
                        sh 'docker rmi t3ddblair/ktor-jenkins:latest'
                    }

                    def version = (BUILD_ID - 1) / 10 + 1
                    sh 'docker build -t t3ddblair/ktor-jenkins:${version} .'
                    sh 'docker tag t3ddblair/ktor-jenkins:${version} t3ddblair/ktor-jenkins:latest'
                }
            }
        }
        stage('Push image to hub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerpwd', variable: 'dockerpwd')]) {
                        sh 'docker login -u t3ddblair -p ${dockerpwd}'
                    }
                    def version = (BUILD_ID - 1) / 10 + 1
                    sh 'docker push t3ddblair/ktor-jenkins:${version}'
                }
            }
        }
        stage('Push latest to hub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerpwd', variable: 'dockerpwd')]) {
                        sh 'docker login -u t3ddblair -p ${dockerpwd}'
                    }
                    sh 'docker push t3ddblair/ktor-jenkins:latest'
                }
            }
        }
    }
}