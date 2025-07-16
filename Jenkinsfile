pipeline {
  agent any

  environment {
    IMAGE_NAME = 'board-service/app'
    TAG = 'latest'
    SONAR_URL = 'http://localhost:9002'
    SONAR_TOKEN = 'sqp_5e6ab5163a76373ee6cdf380632c469179072b02'
  }

  stages {
    stage('Checkout') {
      steps {
        git branch: 'master', url: 'https://github.com/acarlosvs/board-service'
      }
    }

    stage('Build & Test') {
      steps {
        sh 'mvn clean verify'
      }
    }

    stage('Static Analysis') {
      steps {
        withSonarQubeEnv('SonarQube') {
          sh "mvn sonar:sonar -Dsonar.host.url=http://sonarqube:9002 -Dsonar.token=sqp_5e6ab5163a76373ee6cdf380632c469179072b02"
        }
      }
    }

    stage('Build Docker Image') {
      steps {
        sh 'docker build -t board-service:latest .'
      }
    }

    stage('Deploy') {
      steps {
        sh 'docker stop app || true && docker rm app || true'
        sh 'docker run -d --name app -p 8082:8082 board-service:latest'
      }
    }
  }
}
