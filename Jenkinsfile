pipeline {
    agent any

    environment {
            // Define the Docker image name as an environment variable
            DOCKER_IMAGE = 'naourestahri/kaddem-app-image:0.0.1-SNAPSHOT'
        }

        stages {
            stage('GIT') {
                steps {
                    git url: 'https://github.com/NaouresTahri/kaddem.git', branch: 'NaouresTahri'
                }
            }

            stage ('COMPILING') {
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
                    sh 'mvn deploy -DskipTests'
                }
            }

            stage('Build Docker Image') {
                steps {
                    sh "docker build -t ${env.DOCKER_IMAGE} ."
                }
            }

            stage('Deploy to DockerHub') {
                steps {
                    sh "echo 'Logging in to Docker Hub'"
                    sh 'docker login -u naourestahri -p Allah123.A.'
                    sh "docker push ${env.DOCKER_IMAGE}"
                }
            }

            stage('Docker Compose') {
                steps {
                    sh 'docker compose -f docker-compose.yml up -d'
                }
            }
    }
}

