package br.com.lucasdevjava.techSolution.service.exception;

import com.google.gson.Gson;

import lombok.Builder;

import java.util.Date;


@Builder
public class GenericException {
  private static long timestamp = new Date().getTime();
  private int status;
  private String error ;
  private String message;
  private String path;

  public  String tojson(){
 return new Gson().toJson(this);
  }

}
