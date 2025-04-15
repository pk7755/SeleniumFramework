pipeline {
    agent any

    environment {
        CHROME_DRIVER_PATH = "/usr/local/bin/chromedriver"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/pk7755/SeleniumFramework.git'
            }
        }

        stage('Run Custom Selenium Test') {
            steps {
                bat '''
                    mvn clean test ^
                        -Denv=test ^
                        -Dexec.mainClass=CustomTestRunner ^
                        -DtestClass=automatedTest.login.LoginTest ^
                        -DtestMethod=loginTest
                '''
            }
        }

        stage('Publish Reports') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
        }
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
