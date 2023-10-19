pipeline {
    agent any

    stages {
        stage('GIT') {
            steps {
                git url: 'https://github.com/NaouresTahri/kaddem.git', branch: 'NaouresTahri'
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

        stage('SonarQube Analysis') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Allah123.A. -Dsonar.host.url=http://192.168.33.10:9000/"
               

            }
        }

        // Ajoutez vos autres stages ici
    }
}
 
