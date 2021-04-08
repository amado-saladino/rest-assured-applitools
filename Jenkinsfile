pipeline {
  agent {
        docker {
            image 'maven:3-alpine'
            args '-v maven-repo:/root/.m2'
        }
    }
  
  stages {
    stage('checkout') {
      steps {
        git(url: "https://github.com/amado-saladino/rest-assured-applitools.git", branch: 'docker')
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
