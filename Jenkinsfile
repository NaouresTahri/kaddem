
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

stage('SonarQube Analysis') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=admin26 -Dsonar.host.url=http://192.168.0.14:9000/"
               

            }
        }

pipeline {
    agent any

    environment {
        NEXUS_URL = 'http://192.168.0.14:8081/'
        NEXUS_CREDENTIALS_ID = '2d4be97fcc31'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Deploy to Nexus') {
            steps {
                script {
                    def nexusRepo = 'votre-nexus-repo'
                    def artifactId = 'votre-artifact-id'
                    def version = 'votre-version'

                    // Compilation et construction de votre projet
                    sh "mvn clean package"

                    // Utilisation de 'mvn deploy' pour déployer l'artefact dans Nexus
                    sh "mvn deploy -DskipTests -DaltDeploymentRepository=${nexusRepo}::default::${env.NEXUS_URL}/repository/${nexusRepo}/"

                    // Vérification du statut de la commande mvn deploy
                    if (currentBuild.resultIsBetterOrEqualTo('SUCCESS')) {
                        echo "Déploiement réussi dans Nexus."
                    } else {
                        error "Échec du déploiement dans Nexus."
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                // Ajoutez des étapes pour déployer votre application, si nécessaire
            }
        }
    }

    post {
        always {
            // Nettoyez ou effectuez des actions post-construction nécessaires
        }
    }
}



 stage('Mvn Clean'){
            steps {
                sh 'mvn clean'
            }
        }
        stage('Mvn Compile'){
            steps {
                sh 'mvn compile'
            }
        }
        stage('Maven Unit Tests') {
            steps {
               sh 'mvn clean  test '
            } 
        }
        stage('compile sonarqube'){
            steps{
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=yesmine26"
            }
        }
       

        // Ajoutez vos autres stages ici
    }
}
 