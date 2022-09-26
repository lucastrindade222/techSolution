package br.com.lucasdevjava.techSolution.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {
    private static final long serialVersionUID = 1L;
    private String surname;

    public Admin( String email, String password,String name,String surname,String avatarUrl) {
        super( email, password,name,avatarUrl);
       this.surname = surname;
    }





}

