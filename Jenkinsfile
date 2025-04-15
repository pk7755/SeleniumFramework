pipeline {
    agent any

    parameters {
        choice(
                    name: 'ENV',
                    choices: ['test', 'stage', 'production'],
                    description: 'Select the environment to run tests in (default: test)'
                )
        string(name: 'EXEC_MAIN_CLASS', defaultValue: 'CustomTestRunner', description: 'Main class to execute')
        string(name: 'TEST_CLASS', defaultValue: 'automatedTest.login.LoginTest', description: 'Test class to run')
        string(name: 'TEST_METHOD', defaultValue: 'loginTest', description: 'Test method to run')
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://your-git-repo-url.git'
            }
        }

        stage('Run Custom Selenium Test') {
            steps {
                bat """
                    mvn clean test ^
                        -Denv=%ENV% ^
                        -Dexec.mainClass=%EXEC_MAIN_CLASS% ^
                        -DtestClass=%TEST_CLASS% ^
                        -DtestMethod=%TEST_METHOD%
                """
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
