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
            stage('Start Containers') {
                steps {
                    sh 'docker start sonarqube'
                    sh 'docker start 64c13a5735d7' // nexus
                    // Add commands to start other containers as needed
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
                    sh '''
                        mvn deploy -DskipTests \
                        -Drepository.username=admin -Drepository.password=Allah123.A. \
                        -DaltDeploymentRepository=nexus::default::http://192.168.33.10:8081/repository/maven-snapshots/
                    '''
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

    }
    post {
                        always {
                            // This will always run, regardless of the result of the pipeline
                            jacoco(
                                execPattern: '**/**.exec',
                                classPattern: '**/classes',
                                sourcePattern: '**/src/main/java',
                                changeBuildStatus: true,
                                minimumInstructionCoverage: '50'
                            )
                        }
                    }
}

