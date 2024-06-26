pipeline {
  agent any
  triggers{
    githubPush()
  }
  stages {
    stage('build'){
      steps {
        sh 'docker build -t 4248-ishan-app .'
      }
    }
    stage('run'){
      steps{
        sh 'docker run -d -p 5000:3000 4248-ishan-app'
      }
    }
    stage('final'){
      steps{
        sh 'docker ps'
      }
    }
    stage('push'){
      steps{
        script{
          withCredentials([string(credentialsId: 'docker-pwd', variable: 'docker')]) {
    sh 'docker login -u thetharz -p ${docker}'
  }
    sh 'docker push 4248-ishan-app'
        }
      }
    }
  }
}
