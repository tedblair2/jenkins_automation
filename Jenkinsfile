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
                    sh 'docker build -t t3ddblair/ktor-jenkins:${BUILD_ID} .'
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
    }
}