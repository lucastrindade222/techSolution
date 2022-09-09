package br.com.lucasdevjava.techSolution.service.impl;


import br.com.lucasdevjava.techSolution.repository.UserRepository;
import br.com.lucasdevjava.techSolution.service.UserService;
import br.com.lucasdevjava.techSolution.service.exception.UniqueFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void checkEmailExists(String email){
        var user = userRepository.findByEmail(email);

        if (user != null) {
            throw new UniqueFieldException("O e-mail já existe no sistema.");
        }

    }



}
