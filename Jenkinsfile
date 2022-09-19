pipeline {
    agent any
    tools {
        maven "MAVEN"
        jdk "JDK"
    }
    stages {
        stage('Initialize'){
            steps{
                sh 'mvn --version' 
                sh 'java --version'
	   }
        }
        stage('Build') {
            steps {
                dir("/var/lib/jenkins/workspace/demopipelinetask/my-app") {
                sh 'mvn -B -DskipTests clean package'
             }
            }
        }
     }
    post {
       always {
        cleanWs() 
      }
   }

}
