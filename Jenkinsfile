pipeline {
    agent {
        label 'agent 1'
    }

    stages {
        stage('Build') {
            steps {
                withDockerRegistry(credentialsId: 'dockerhub-ngoxuanchien', url: 'https://index.docker.io/v1/') {
                    sh 'mvn clean compile jib:build'
                }
            }
        }
        stage('Test') {
            steps {
//                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker-compose up'
            }
        }
    }
}