package br.com.lucasdevjava.techSolution.repository;

import br.com.lucasdevjava.techSolution.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer>, PagingAndSortingRepository<Products,Integer> {
}
