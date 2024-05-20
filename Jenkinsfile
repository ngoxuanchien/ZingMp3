pipeline {
    agent {
        label 'agent 1'
    }

    stages {
        stage('Check Containers') {
            steps {
                script {
                    def containers = ['keycloak', 'mysql-keycloak', 'mysql-playlist', 'mysql-playback']
                    for (container in containers) {
                        sh(script: "if docker ps -a | grep -q ${container}; then echo 'Container ${container} exists.'; else echo 'Container ${container} does not exist.'; fi", returnStdout: true)
                    }
                }
            }
        }

        stage('Setup') {
            steps {
                sh 'docker-compose -f init-docker-compose.yml stop'
                sh 'docker-compose -f init-docker-compose.yml rm -f'
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