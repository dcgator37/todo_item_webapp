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
            steps {
                sh 'mvn clean package'
            }

        }

        stage('Test') {
            steps {
                sh 'mvn test'
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