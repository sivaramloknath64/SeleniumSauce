pipeline {
    agent any

    environment {
        DOCKER_COMPOSE_FILE = "docker-compose.yaml"
        GRID_URL = "http://localhost:4444/wd/hub"
        // PROJECT_DIR will be automatically set to the directory of the Git repo
        PROJECT_DIR = "${env.WORKSPACE}" // This is where the repo will be cloned
    }

    stages {
        stage('Clone Repository') {
            steps {
                // Checkout the code from the Git repository
                checkout scm  // This will checkout the repository from the Jenkins job's configured SCM
                echo "Repository cloned successfully to ${env.WORKSPACE}"
            }
        }

        stage('Prepare') {
            steps {
                script {
                    // Ensure Docker is running
                    bat "docker --version"
                    bat "docker-compose --version"

                    // Start the Selenium Grid using Docker Compose
                    bat "docker-compose -f ${DOCKER_COMPOSE_FILE} up -d"

                    // Ensure the Selenium Grid is up and running
                    bat "docker ps"
                }
            }
        }

        stage('Run Selenium Tests') {
            steps {
                script {
                    // Running tests in the directory where the Git project is cloned
                    dir("${PROJECT_DIR}") {
                        // For example, if your project is a Maven project:
                        bat "mvn clean test"
                    }
                }
            }
        }

        stage('Tear Down') {
            steps {
                script {
                    // Clean up the Docker containers after tests are completed
                    bat "docker-compose -f ${DOCKER_COMPOSE_FILE} down"
                }
            }
        }
    }
}
