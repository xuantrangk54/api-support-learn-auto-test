pipeline {
    agent any

    tools {
        maven 'Maven 3' // Tên Maven đã cấu hình trong Jenkins
        jdk 'Java 21'   // Tên JDK đã cài trên Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/username/project.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Deploy') {
            steps {
                // Ví dụ deploy lên server hoặc copy jar
                sh 'cp target/*.jar /path/to/deploy/'
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
