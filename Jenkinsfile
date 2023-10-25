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

         stages {
        stage('GIT') {
            steps {
                git url: 'https://github.com/NaouresTahri/kaddem.git', branch: 'yesmineDevops'
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