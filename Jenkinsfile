pipeline {
    agent {
        // Todo: change to your agent
        label 'nxc-hcmus-1'
    }

    environment {

        // Todo: change to your email credentials
        EMAIL = credentials('nxc-email')
    }

    stages {
        stage("Init") {
            steps {
                script {
                    sh '''
                        cd init 
                        docker-compose up -d
                        docker-compose ps
                        cd ..
                    '''
                }
            }
        }
        stage('Build') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'master') {

                        // Todo: you must change the value of the image in the "pom.xml" file after fun
                        // Todo: change to your docker registry
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
                sh 'mvn test'
            }
        }
        stage('Deploy to QA server') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'master') {

                        def emailUser = env.EMAIL_USR
                        def emailPassword = env.EMAIL_PSW

                        def envContent = """
                            MAIL_USERNAME=${emailUser}
                            MAIL_PASSWORD=${emailPassword}
                        """.stripIndent()

                        writeFile file: '.env', text: envContent

                        sh 'sudo aa-remove-unknown'
                        sh 'docker-compose stop'
                        sh 'docker-compose rm -f'
                        sh 'docker-compose pull'
                        sh 'docker-compose up -d'
                        sh 'docker image prune -f'
                        sh 'docker system prune -f'
                        sh 'docker volume prune -f'
                    } else {
                        echo 'skip deploy'
                    }
                }
            }
        }
    }
}