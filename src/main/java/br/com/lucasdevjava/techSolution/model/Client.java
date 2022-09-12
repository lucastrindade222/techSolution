package br.com.lucasdevjava.techSolution.model;

import lombok.*;

;
import javax.persistence.Entity;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Client extends Person {
    private static final long serialVersionUID = 1L;

    private String fantasyname;

    public Client(String email, String password, String name,String lastName, String avatarUrl) {
        super(email, password, name,lastName, avatarUrl);

    }

}
