package br.com.lucasdevjava.techSolution.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    private String zipCode;

    private String city;

    private String district;

    private String road;

    private String numero;

    private String complemento;

    private String state;

}
