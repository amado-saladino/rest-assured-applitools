pipeline {
  agent any
  tools {
        maven "3.6.3" //You need to add a maven with name "3.6.3" in the Global Tools Configuration page
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
