package br.com.lucasdevjava.techSolution.service;


import br.com.lucasdevjava.techSolution.model.Admin;

public interface AdminService {
    public abstract Admin save(Admin admin);
    public abstract  Admin searchForMe();
    public  abstract  Admin findById(Integer id);
}
