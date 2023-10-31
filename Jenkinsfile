pipeline {
    agent any

    stages {
        stage('GIT') {
            steps {
                git url: 'https://github.com/NaouresTahri/kaddem.git', branch: 'yesmineDevops'
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
               sh 'mvn clean  test '
            } 
        }

stage('SonarQube Analysis') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=yesmine26 -Dsonar.host.url=http://192.168.0.14:9000/"
               

            }
        }

}
