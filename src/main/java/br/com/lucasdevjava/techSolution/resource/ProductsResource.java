package br.com.lucasdevjava.techSolution.resource;

import br.com.lucasdevjava.techSolution.dto.ClientNewDTO;
import br.com.lucasdevjava.techSolution.dto.ProductsNewDTO;
import br.com.lucasdevjava.techSolution.model.Admin;
import br.com.lucasdevjava.techSolution.model.Client;
import br.com.lucasdevjava.techSolution.model.Products;
import br.com.lucasdevjava.techSolution.service.ProductsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("products")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductsResource {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductsService productsService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Products> save(@Validated @RequestBody ProductsNewDTO productsNewDTO){
        var product = modelMapper.map(productsNewDTO, Products.class);
        product = productsService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
    public  ResponseEntity<List<Products>> findall(){
        List<Products> productsList = productsService.findAll();
        return  ResponseEntity.ok().body(productsList);
    }


    @DeleteMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> delete(@RequestParam(value = "id")  Integer id) {
        productsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
