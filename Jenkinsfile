pipeline{
  agent any

  stages ("Build") { 
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



