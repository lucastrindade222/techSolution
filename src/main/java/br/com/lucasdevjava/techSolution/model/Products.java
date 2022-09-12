package br.com.lucasdevjava.techSolution.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productname;
    private String provider;
    private  String productValue;
    private String imageUrl;


}
