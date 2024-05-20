pipeline {
    agent {
        label 'agent 1'
    }

    stages {
        stage('Setup') {
            steps {
                sh 'docker-compose -f init-docker-compose.yml up -d'
            }
        }

        stage('Build') {
            steps {
                withDockerRegistry(credentialsId: 'dockerhub-ngoxuanchien', url: 'https://index.docker.io/v1/') {
                    sh 'mvn clean compile jib:build'
                }
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
                echo 'Test'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker-compose stop'
                sh 'docker-compose rm -f'
                sh 'docker-compose pull'
                sh 'docker-compose up -d'
            }
        }
    }
}