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
        stage('compile sonarqube'){
            steps{
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=yesmine26"
            }
        }
