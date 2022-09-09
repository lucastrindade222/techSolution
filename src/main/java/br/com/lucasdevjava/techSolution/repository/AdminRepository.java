package br.com.lucasdevjava.techSolution.repository;


import br.com.lucasdevjava.techSolution.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository  extends JpaRepository<Admin, Integer>,PagingAndSortingRepository<Admin,Integer>{
    Admin findByEmail(String email);
}
