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
	
	 stage('Build App Image') {
                steps {
                    sh 'cp /app/kaddem/target/kaddem-0.0.1-SNAPSHOT.jar .'
                    // Build the Docker image with the name that matches your docker-compose file
                    sh 'docker build -t kaddem-app-image:latest .'
                }
            }

            stage('Deploy App Image in  DockerHub') {
                steps {
                    // Tag the image for DockerHub
                    sh 'docker tag kaddem-app-image:latest naourestahri/kaddem-app-image:latest'

                    // Login to DockerHub using your credentials (directly as you mentioned)
                    sh 'docker login -u naourestahri -p Allah123.A.' // Replace with your actual username and password

                    // Push the image to DockerHub
                    sh 'docker push naourestahri/kaddem-app-image:latest'
                }
            }

    
	    stage('Docker Compose Up') {
            steps {
                // Run docker-compose up from the directory containing the docker-compose.yml
                sh 'docker compose docker-compose.yml up -d'
            }
        }

    }
}
 
