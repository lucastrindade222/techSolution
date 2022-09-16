package br.com.lucasdevjava.techSolution.service.impl;

import br.com.lucasdevjava.techSolution.model.Admin;
import br.com.lucasdevjava.techSolution.model.Client;
import br.com.lucasdevjava.techSolution.model.Products;
import br.com.lucasdevjava.techSolution.repository.ProductsRepository;
import br.com.lucasdevjava.techSolution.service.ProductsService;
import br.com.lucasdevjava.techSolution.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductsServiceImpl implements ProductsService {
    @Autowired
    private ProductsRepository productsRepository;


    @Override
    public Products save(Products products) {

        return productsRepository.save(products);
    }

    @Override
    public Products faindById(Integer id) {
        Optional<Products> admin = productsRepository.findById(id);
        return admin.orElseThrow(()-> new ObjectNotFoundException("Admin não encontrado"));
    }

    @Override
    public List<Products> findAll() {
        return productsRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        var product = this.faindById(id);
    productsRepository.delete(product);
    }
}
