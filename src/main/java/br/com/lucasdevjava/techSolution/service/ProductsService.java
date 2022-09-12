package br.com.lucasdevjava.techSolution.service;
import br.com.lucasdevjava.techSolution.model.Products;

import java.util.List;
import java.util.UUID;

public interface ProductsService {

    public abstract Products save(Products products);


 public abstract  Products faindById(Integer id);
    public abstract List<Products> findAll();

    public  abstract  void delete(Integer id);

}
