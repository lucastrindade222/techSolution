package br.com.lucasdevjava.techSolution.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminNewDTO {

  private String email;
  private String password;
  private String name;
  private String surname;


}
