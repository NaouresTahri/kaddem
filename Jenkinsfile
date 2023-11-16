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

stage('SonarQube Analysis') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=yesmine26 -Dsonar.host.url=http://192.168.0.14:9000/"
               

            }
        }

stage('Nexus'){
            steps{
                  script {
                    //Configurez les informations d'authentification pour Maven (ex. : NEXUS_USERNAME et NEXUS_PASSWORD)
                    def nexusUsername = 'admin'
                    def nexusPassword = 'yesmine26'

                    // Exécutez la phase "deploy" de Maven
                    sh "mvn deploy --settings /usr/share/maven/conf/settings.xml -Dusername=${nexusUsername} -Dpassword=${nexusPassword}"
                   
                }
            }
        }












}
}
