pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: 'yesmineDevops']], userRemoteConfigs: [[url: 'https://github.com/NaouresTahri/kaddem.git']])                
            }
        }

        stage('Clean and Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
    }
}
