pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/PoojaSN-Automates/Selenium_Automation.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }
}