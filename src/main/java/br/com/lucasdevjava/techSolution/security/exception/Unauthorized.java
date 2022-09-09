package br.com.lucasdevjava.techSolution.security.exception;

public class Unauthorized extends RuntimeException{
    private static final long serialVersionUID = 1L;

   public Unauthorized(String msg ){
    super(msg);
   }
   public  Unauthorized(String msg ,Throwable cause){
       super(msg,cause);
   }

}
