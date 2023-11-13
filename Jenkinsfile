pipeline {
    agent any
environment {
            // Define the Docker image name as an environment variable
            DOCKER_IMAGE = 'yesmineeladab/kaddem:0.0.1'
        }

    stages {
        stage('GIT') {
            steps {
                git branch :'yesmineDevops',
                // Get some code from a GitHub repository
                url : 'git@github.com:NaouresTahri/kaddem.git'
                credentialsId : '1234'
            }
        }
  stage('Maven Clean') {
            steps {
                // Cette étape exécute la commande Maven clean
                sh 'mvn clean'
            }
        }

        stage('Maven Compile') {
            steps {
                // Cette étape exécute la commande Maven compile
                sh 'mvn compile'
            }
        }

        stage('Maven Unit Tests') {
            steps {
               sh 'mvn test'
            } 
        }


}
}