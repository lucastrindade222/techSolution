package br.com.lucasdevjava.techSolution.security.exception;


public class JWTAuthenticationFailure extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public JWTAuthenticationFailure(String msg ){
        super(msg);
    }
    public  JWTAuthenticationFailure(String msg ,Throwable cause){
        super(msg,cause);
    }

}
