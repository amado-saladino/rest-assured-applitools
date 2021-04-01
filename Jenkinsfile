pipeline {
  agent any
  tools {
        maven "3.6.3"
    }
  
  stages {
    stage('checkout') {
      steps {
        git(url: 'https://github.com/amado-saladino/rest-assured-applitools.git', branch: 'master')
      }
    }

    stage('test') {
      steps {
        sh 'mvn clean test'
      }
      post {
            always {
                junit 'target/surefire-reports/**/*.xml' 
            }
        }
    }

  }
}
