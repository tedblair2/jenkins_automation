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
                    sh 'docker build -t t3ddblair/ktor-jenkins:${BUILD_ID} .'
                    sh 'docker tag t3ddblair/ktor-jenkins:${BUILD_ID} t3ddblair/ktor-jenkins:latest'
                }
            }
        }
        stage('Push image to hub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerpwd', variable: 'dockerpwd')]) {
                        sh 'docker login -u t3ddblair -p ${dockerpwd}'
                    }
                    sh 'docker push t3ddblair/ktor-jenkins:${BUILD_ID}'
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
        stage('Deploy to k8s') {
            steps {
                script {
                    sh 'kubectl apply -f ktor.jenkins.yaml'
                }
            }
        }
    }
}