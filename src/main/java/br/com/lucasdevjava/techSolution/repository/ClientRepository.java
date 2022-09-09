package br.com.lucasdevjava.techSolution.repository;


import br.com.lucasdevjava.techSolution.model.Client;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ClientRepository extends PersonBaseRepository<Client> {
    public Client findByEmail(String email);
}
