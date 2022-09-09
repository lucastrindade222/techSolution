package br.com.lucasdevjava.techSolution.security.exception;

public class ProxyAuthenticationRequired extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ProxyAuthenticationRequired(String msg ){
        super(msg);
    }
    public  ProxyAuthenticationRequired(String msg ,Throwable cause){
        super(msg,cause);
    }

}
