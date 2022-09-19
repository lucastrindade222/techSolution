pipeline{
  agent any

  stages {
   
   steps{
    sh "mvn  -version"
    sh "mvn claen install"
    }
 }  
  post{
   always{
     cleanWs() 
   }

  }


}



