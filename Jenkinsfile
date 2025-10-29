pipeline {
    agent any

    tools {
        maven 'Maven 3' // Tên Maven đã cấu hình trong Jenkins
        jdk 'Java 21'   // Tên JDK đã cài trên Jenkins
    }

    environment {
        IMAGE_NAME = "supportkarateapi:latest"
    }

    stages {
       

        stage('Checkout') {
            steps {
                sh 'java -version'
                sh 'echo $JAVA_HOME'
                git branch: 'main', url: 'https://github.com/xuantrangk54/api-support-learn-auto-test.git'

            }
        }

        stage('Build') {
            steps {
                sh "docker build -t ${IMAGE_NAME} ."
            }
        }

        stage('Test') {
            steps {
                // Chạy container tạm thời để chạy test
                sh "docker run --entrypoint mvn --rm ${IMAGE_NAME} mvn test -Dspring.main.web-application-type=none"
            }
        }
    
        stage('Push Docker Image') {
            steps {
                // Nếu có registry, ví dụ Docker Hub
                // withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                //     sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin"
                //     sh "docker tag ${IMAGE_NAME} $DOCKER_USER/${IMAGE_NAME}"
                //     sh "docker push $DOCKER_USER/${IMAGE_NAME}"
                // }
                sh "echo 'skip push image'"
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Deploy') {
            steps {
                // Ví dụ deploy container lên server
                sh """
                    docker stop supportkarateapi || true
                    docker rm supportkarateapi || true
                    docker run -d --name supportkarateapi -p 8080:8080 $DOCKER_USER/${IMAGE_NAME}
                """
            }
        }
    }

    post {
        success {
            echo 'Build & Deploy thành công!'
        }
        failure {
            echo 'Có lỗi xảy ra!'
        }
    }
}
