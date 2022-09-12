package br.com.lucasdevjava.techSolution.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person extends User{
    private  String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date nascimento;
    @Embedded
    private Telephone telefone;
    @Embedded
    private Address endereco;

    public Person(String email, String password, String name,String lastName, String avatarUrl) {
        super(email, password,name, avatarUrl);
        this.lastName = lastName;

    }
}
