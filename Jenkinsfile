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
	stage('Nexus') {
            steps {
                // maven deploy with skiping tests
                sh 'mvn deploy -DskipTests'
            }
        }
     

    
        stage('Build and Deploy Docker Im BackAPP') {
            steps {
                // Building the Docker image using the Dockerfile in your project directory
                sh 'docker build -t naourestahri/kaddem-backend:latest .'
                
                // Login to DockerHub using manual credentials
                sh 'docker login -u naoures.tahri@esprit.tn -p Allah123.A.' // Replace with your actual password
                
                // Pushing the image to DockerHub
                sh 'docker push naourestahri/kaddem-backend:latest'
            }
        }
    
	stage('Docker Compose Up') {
            steps {
                sh 'docker compose up -d'
            }
        }


    }
}
 
