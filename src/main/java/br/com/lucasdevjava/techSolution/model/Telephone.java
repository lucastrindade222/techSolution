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
public class Telephone implements Serializable {
    private static final long serialVersionUID = 1L;
    private String areaCode;

    private String number;
}
