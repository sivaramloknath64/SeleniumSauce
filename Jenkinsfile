pipeline {
    agent any

    environment {
        DOCKER_COMPOSE_FILE = "docker-compose.yml"
        GRID_URL = "http://localhost:4444/wd/hub"
        PROJECT_DIR = "/path/to/your/selenium/project"
    }

    stages {
        stage('Clone Repository') {
            steps {
                // Checkout the code from the Git repository
                git credentialsId: 'your-credentials-id', url: 'https://github.com/your-username/your-repository.git'
            }
        }

        stage('Prepare') {
            steps {
                script {
                    // Ensure Docker is running
                    sh "docker --version"
                    sh "docker-compose --version"
                }
            }
        }

        stage('Start Docker Grid') {
            steps {
                script {
                    // Start the Docker Compose grid
                    sh "docker-compose -f ${DOCKER_COMPOSE_FILE} up -d"
                    // Wait for the Grid to initialize
                    sleep(time: 10, unit: 'SECONDS')
                }
            }
        }

        stage('Run Selenium Tests') {
            steps {
                script {
                    // Execute your Selenium tests (assumed to be a Java Maven project)
                    sh "mvn clean test -Dselenium.grid.url=${GRID_URL} -f ${PROJECT_DIR}/pom.xml"
                }
            }
        }

        stage('Stop Docker Grid') {
            steps {
                script {
                    // Stop and remove all containers from the grid
                    sh "docker-compose -f ${DOCKER_COMPOSE_FILE} down"
                }
            }
        }

        stage('Archive Test Results') {
            steps {
                script {
                    // Archive the test results as Jenkins artifacts
                    junit '**/target/test-*.xml' // Assuming your tests generate test reports in XML format (JUnit reports)
                    archiveArtifacts allowEmptyArchive: true, artifacts: '**/target/*.jar, **/target/test-*.xml', followSymlinks: false
                }
            }
        }
    }

    post {
        always {
            script {
                // Clean up Docker containers in case of failure
                sh "docker-compose -f ${DOCKER_COMPOSE_FILE} down"
            }
        }
    }
}
