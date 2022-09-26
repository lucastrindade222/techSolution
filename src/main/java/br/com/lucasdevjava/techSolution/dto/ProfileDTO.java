package br.com.lucasdevjava.techSolution.dto;

import br.com.lucasdevjava.techSolution.model.Address;
import br.com.lucasdevjava.techSolution.model.Telephone;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDTO {

        private Integer id;
        private String email;
        private String password;
        private String name;
        private String avataarUri;
        private  String lastName;
        private Date nascimento;
        private Telephone telefone;
        private Address endereco;
        private String surname;
        private String fantasyname;

}
