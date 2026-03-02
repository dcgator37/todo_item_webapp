pipeline {
    agent any

    environment {
        IMAGE_NAME = "todo-app"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }

        }

        stage('Build') {
            steps { withEnv(['PATH+EXTRA=/usr/sbin:/usr/bin:/sbin:/bin']) {
                sh 'mvn clean package'
            }
            }
        }

        stage('Test') {
            steps { withEnv(['PATH+EXTRA=/usr/sbin:/usr/bin:/sbin:/bin']) {
                sh 'mvn test'
            }
            }
        }


    }

    post {
        success {
            echo 'Build, tests, and Docker image completed successfully.'
        }
        failure {
            echo 'Build failed.'
        }
    }
}