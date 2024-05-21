pipeline {
    agent {
        label 'nxc-hcmus-2'
    }

    stages {
//        stage('Setup') {
//            steps {
//                sh 'docker-compose -f keycloak-docker-compose.yml rm -s -f -v'
//                sh 'docker-compose -f keycloak-docker-compose.yml up -d'
//
//                sh 'docker-compose -f init-docker-compose.yml rm -s -f'
//                sh 'docker-compose -f init-docker-compose.yml up -d'
//            }
//        }

        stage('Build') {
            steps {
                withDockerRegistry(credentialsId: 'dockerhub-ngoxuanchien', url: 'https://index.docker.io/v1/') {
                    sh 'mvn clean compile jib:build'
                }
            }
        }
        stage('Test') {
//            steps {
//                sh 'mvn test'
//            }
//            post {
//                always {
//                    junit '**/target/surefire-reports/*.xml'
//                }
//            }

            try {
                sh 'mvn test'

                publishHTML target: [
                        allowMissing: false,
                        alwaysLinkToLastBuild: false,
                        keepAll: true,
                        reportDir: 'build/reports/tests/test',
                        reportFiles: 'index.html',
                        reportName: 'Unit Test Report'
                ]
            } catch (err) {
                step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
                throw err
            }
        }
        stage('Deploy to QA server') {
            steps {
                sh 'docker-compose rm -s -f'
                sh 'docker-compose pull'
                sh 'docker-compose up -d'
                sh 'docker image prune -f'
                sh 'docker system prune -f'

            }
        }
        stage('Deploy to PROD server') {
            steps {
                sh 'docker-compose rm -s -f'
                sh 'docker-compose pull'
                sh 'docker-compose up -d'
                sh 'docker image prune -f'
                sh 'docker system prune -f'
            }
        }
    }
    post {
        always {
            mail to: '20120046@student.hcmus.edu.vn',
            subject: "jenkins build:${currentBuild.currentResult}: ${env.JOB_NAME}",
            body: "${currentBuild.currentResult}: Job ${env.JOB_NAME}\nMore Info can be found here: ${env.BUILD_URL}"
        }


    }
}