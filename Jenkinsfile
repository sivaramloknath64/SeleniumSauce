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
              echo "cloned reposuccessfull"
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
}
