pipeline{
  agent{label 'linux' }
  tools {
  maven '3.8.6'
  jdk '11'
  }

  stages { 
     stage ("Build") {
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



