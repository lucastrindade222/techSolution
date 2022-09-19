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
                dir("/home/lucas/Documentos/GitHub/techSolution") {
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
