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

        
stage('SonarQube Analysis') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=admin26 -Dsonar.host.url=http://192.168.0.14:9000/"
               

            }
        }