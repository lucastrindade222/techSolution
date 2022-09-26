package br.com.lucasdevjava.techSolution.service;


import br.com.lucasdevjava.techSolution.dto.ProfileDTO;
import br.com.lucasdevjava.techSolution.model.User;

public interface UserService {
    public abstract  void checkEmailExists(String email);

    public abstract User searchForMe();
    public abstract User findById(Integer id) ;

}
