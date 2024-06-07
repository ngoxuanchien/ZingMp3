pipeline {
    agent {
        label 'nxc-hcmus-1'
    }

    stages {
        stage('Build') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'master') {
                        withDockerRegistry(credentialsId: 'dockerhub-ngoxuanchien', url: 'https://index.docker.io/v1/') {
                            sh 'mvn clean compile install -DskipTests jib:build'
                            echo 'Build docker image success'
                        }
                    } else {
                        sh 'mvn clean install -DskipTests'
                    }
                }
            }
        }
        stage('Test') {
            steps {
//                sh 'mvn test'
                echo 'test'
            }
//            post {
//                always {
//                    junit testResults: '**/target/surefire-reports/TEST-*.xml', skipPublishingChecks: true
//                }
//            }
        }
        stage('Deploy to QA server') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'master') {
                        sh 'docker-compose down -v'
                        sh 'rm -rf ./database'
                        sh 'docker-compose pull'
                        sh 'docker-compose up -d'
                        sh 'docker image prune -f'
                        sh 'docker system prune -f'
                    } else {
                        echo 'skip deploy'
                    }
                }
            }
        }
//        stage('Deploy to PROD server') {
//            agent {
//                label 'nxc-hcmus-1'
//            }
//            steps {
//                script {
//                    if (env.BRANCH_NAME == 'master') {
//                        sh 'docker-compose down'
//                        sh 'docker-compose pull'
//                        sh 'docker-compose up -d'
//                        sh 'docker image prune -f'
//                        sh 'docker system prune -f'
//                    } else {
//                        echo 'skip deploy'
//                    }
//                }
//            }
//        }
    }
//    post {
//        always {
//            mail to: '20120046@student.hcmus.edu.vn',
//            subject: "jenkins build:${currentBuild.currentResult}: ${env.JOB_NAME}",
//            body: "${currentBuild.currentResult}: Job ${env.JOB_NAME}\nMore Info can be found here: ${env.BUILD_URL}"
//        }
//
//
//    }
}