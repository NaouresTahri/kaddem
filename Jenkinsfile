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
               sh 'mvn clean  test '
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




stage('Build Docker Image') {
                steps {
                    sh "docker build -t ${environment.DOCKER_IMAGE} ."
                }
            }

            stage('Deploy to DockerHub') {
                steps {
                    sh "echo 'Logging in to Docker Hub'"
                    sh 'docker login -u yesmine993 -p yesmine26'
                    sh "docker push ${environment.DOCKER_IMAGE}"
                }
            }

stage('Deploy our image backend') { 
            steps { 
                script {
                    sh 'docker push yesmineeladab/kaddem:0.0.1'
                }
            } 
        }

            stage('Docker Compose') {
                steps {
                    sh 'docker compose -f docker-compose.yml up -d'
                }
            }








}
}
