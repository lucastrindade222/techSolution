package br.com.lucasdevjava.techSolution.repository;


import br.com.lucasdevjava.techSolution.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;




@NoRepositoryBean
public interface PersonBaseRepository<T extends User> extends JpaRepository<T, Integer> {

}