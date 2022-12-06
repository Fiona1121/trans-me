Jenkinsfile (Declarative Pipeline)
/* Requires the Docker Pipeline plugin */
pipeline {
    agent { docker { image "maven:3.8.6-openjdk-11-slim" } }

    stages {
        stage("build") {
            steps {
                echo "building the application..."
            }
        }

        stage("test") {
            steps {
                echo "testing the application..."
            }
        }

        stage("deploy") {
            steps {
                echo "deploying the application"
            }
        }
    }
}
