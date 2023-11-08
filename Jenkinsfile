pipeline {
    agent any

    environment {
        // Define the Docker image name as an environment variable
        DOCKER_IMAGE = 'naourestahri/kaddem-app-image:0.0.1-SNAPSHOT'
        // Define the credentials directly as environment variables (not recommended)
        DOCKER_USERNAME = 'naourestahri'
        DOCKER_PASSWORD = 'Allah123.A.'
        SONAR_LOGIN = 'admin'
        SONAR_PASSWORD = 'Allah123.A.'
        // Define the SonarQube server URL
        SONAR_HOST_URL = 'http://192.168.33.10:9000/'
    }

    stages {
        stage('GIT') {
            steps {
                git url: 'https://github.com/NaouresTahri/kaddem.git', branch: 'main'
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
                sh "mvn sonar:sonar -Dsonar.login=${env.SONAR_LOGIN} -Dsonar.password=${env.SONAR_PASSWORD} -Dsonar.host.url=${env.SONAR_HOST_URL}"
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

        stage('Push to DockerHub') {
            steps {
                sh "echo 'Logging in to Docker Hub'"
                sh "docker login -u ${env.DOCKER_USERNAME} -p ${env.DOCKER_PASSWORD}"
                sh "docker push ${env.DOCKER_IMAGE}"
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker-compose -f docker-compose.yml up -d'
            }
        }
    }
}

