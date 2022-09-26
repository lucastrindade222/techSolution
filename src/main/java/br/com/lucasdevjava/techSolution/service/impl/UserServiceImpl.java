package br.com.lucasdevjava.techSolution.service.impl;


import br.com.lucasdevjava.techSolution.dto.ProfileDTO;
import br.com.lucasdevjava.techSolution.dto.RoleDTO;

import br.com.lucasdevjava.techSolution.model.User;
import br.com.lucasdevjava.techSolution.repository.AdminRepository;
import br.com.lucasdevjava.techSolution.repository.ClientRepository;
import br.com.lucasdevjava.techSolution.repository.UserRepository;

import br.com.lucasdevjava.techSolution.service.UserService;
import br.com.lucasdevjava.techSolution.service.exception.ObjectNotFoundException;
import br.com.lucasdevjava.techSolution.service.exception.UniqueFieldException;
import br.com.lucasdevjava.techSolution.utils.JWTUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;




import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public void checkEmailExists(String email) {
        var user = userRepository.findByEmail(email);

        if (user != null) {
            throw new UniqueFieldException("O e-mail já existe no sistema.");
        }

    }

    @Override
    public User searchForMe() {
        var roles = jwtUtil.getRolesByRequest(request);
        var role = roles.get(0).getNomeRole();
        String email = jwtUtil.getEmailByRequest(request);

        switch (role) {
            case "ROLE_ADMIN":
                return this.admin(email);

            case "CLIENTE":
                return this.cliente(email);

        }

        return null;
    }

    protected User admin(String email) {
      //  String email, String password,String name,String surname,String avatarUrl
        var admin = adminRepository.findByEmail(email);

        return admin;
    }

    protected User cliente(String email) {

         var cliente = clientRepository.findByEmail(email);

        return cliente;
    }


    @Override
    public User findById(Integer id) {
        Optional<User> admin = userRepository.findById(id);
        return admin.orElseThrow(() -> new ObjectNotFoundException("Admin não encontrado"));
    }


}