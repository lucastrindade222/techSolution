package br.com.lucasdevjava.techSolution.service;


import br.com.lucasdevjava.techSolution.model.Client;

public interface ClientService {
    public  abstract Client findById(Integer id);

    public abstract Client save(Client client);

}
