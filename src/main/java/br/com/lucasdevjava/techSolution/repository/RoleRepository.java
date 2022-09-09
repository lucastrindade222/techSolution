package br.com.lucasdevjava.techSolution.repository;


import br.com.lucasdevjava.techSolution.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>, PagingAndSortingRepository<Role,Integer> {
    Role findBynameRole(String nomeRole);


}
