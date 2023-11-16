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

            stage('JaCoCo') {
                 steps {
                      sh 'mvn jacoco:report'
                 }
            }

            stage('Maven Package') {
                steps {
                    sh 'mvn clean package'
                }
            }
            stage('Start SONARQUBE CONTAINER') {
                steps {
                    sh 'docker start sonarqube'
                    sh 'sleep 120'
                }
            }
            stage('Start NEXUS CONTAINER') {
                 steps {
                     sh 'docker start 64c13a5735d7'
                     sh 'sleep 120'
                 }
            }

            stage('SONARQUBE') {
                steps {
                    //sh "docker start sonarqube"
                    sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Allah123.A. -Dsonar.host.url=http://192.168.33.10:9000/"
                    
                }
            }

            stage('Nexus') {
                steps {
                    //sh "docker start 64c13a5735d7"
                    sh "mvn deploy -DskipTests"

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

            stage(' Prometheus') {
                    steps {
                        script {
                            sh "docker start 879171b52c20"
                        }
                    }
                }
            stage(' Grafana') {
                    steps {
                        script {
                            sh "docker start b94e81e71e2a"
                        }
                    }
                }

            stage('Verify Ngrok') {
                    steps {
                        script {
                      // Check if ngrok's web interface is up by requesting its homepage
                      sh "curl -o /dev/null -s -f http://192.168.33.10:4040"
                      echo 'Ngrok is up and running!'
                        }
                    }
            }


    }
    post {
                        always {
                            // This will always run, regardless of the result of the pipeline
                            jacoco(
                                execPattern: '**/**.exec',
                                classPattern: '**/classes',
                                sourcePattern: '**/src/main/java',
                                changeBuildStatus: false,
                                minimumInstructionCoverage: '50'
                            )
                        }
                    }
}

