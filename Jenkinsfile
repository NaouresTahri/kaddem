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
     stage('Maven Package') {
            steps {
                        // Run the maven package command
                        sh 'mvn clean package'
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
                // No need to copy the JAR file, Docker build will handle it
                // Build the Docker image with the specific tag
                sh 'docker build -t naourestahri/kaddem-app-image:0.0.1-SNAPSHOT .'
            }
        }

        stage('Deploy App Image in DockerHub') {
            steps {
                // Login to DockerHub
                sh 'docker login -u naourestahri -p Allah123.A.'

                // Push the image to DockerHub with the specific version
                sh 'docker push naourestahri/kaddem-app-image:0.0.1-SNAPSHOT'
            }
        }


        stage('Check Environment') {
            steps {
                sh 'pwd'
                sh 'ls -la /app/kaddem'
            }
        }

        stage('Docker Compose Up') {
            steps {
                dir('/app/kaddem') {
                    // Pull the latest images
                    sh 'docker compose pull'
                    // Start up the application
                    sh 'docker compose up -d'
                }
            }
        }



    }
}
 
