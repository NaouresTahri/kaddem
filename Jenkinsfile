pipeline {
    agent any

    stages {
        stage('GIT') {
            steps {
                git url: 'https://github.com/NaouresTahri/kaddem.git', branch: 'NaouresTahri'
            }
        }
      
	 stage ('COMPILING'){
		steps {
		sh 'mvn clean compile'

	    }		
	}
	
	 stage('JUNIT/MOCHITO') {
            steps {
                
                sh 'mvn test'
            }
        }

        stage('SONARQUBE') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Allah123.A. -Dsonar.host.url=http://192.168.33.10:9000/"
               

            }
        }

    }
}
 
