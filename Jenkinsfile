pipeline{
  agent any

  stages { 
     stage {
        steps {
           sh "mvn  -version"
           sh "mvn clean install"
        }
    }
 }  
  post{
   always{
     cleanWs() 
   }

  }


}



