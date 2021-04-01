pipeline {
  agent any
  stages {
    stage('checkout') {
      steps {
        git(url: 'https://github.com/amado-saladino/rest-assured-applitools.git', branch: 'master')
      }
    }

    stage('setup') {
      environment {
        PATH = '$PATH:/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/maven/bin'
      }
      steps {
        tool(name: 'maven', type: 'maven')
      }
    }

    stage('test') {
      steps {
        sh 'mvn clean test'
      }
    }

  }
}