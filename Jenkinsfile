pipeline {
    agent {
        label 'agent 1'
    }

    stages {
        stage('Setup') {
            steps {
                sh 'docker-compose -f keycloak-docker-compose.yml rm -s -f -v'
                sh 'docker-compose -f keycloak-docker-compose.yml up -d'

                sh 'docker-compose -f init-docker-compose.yml rm -s -f'
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
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker-compose rm -s -f'
                sh 'docker-compose pull'
                sh 'docker-compose up -d'
            }
        }
    }
    post {
        success {
            // Gửi thông báo khi build thành công
            mail to: '20120046@student.hcmus.edu.vn',
                    subject: "Build Successful",
                    body: "The build was successful!"
        }
        failure {
            // Gửi thông báo khi build thất bại
            mail to: '20120046@student.hcmus.edu.vn',
                    subject: "Build Failed",
                    body: "The build has failed. Please check the Jenkins console output for more details."
        }
    }
}