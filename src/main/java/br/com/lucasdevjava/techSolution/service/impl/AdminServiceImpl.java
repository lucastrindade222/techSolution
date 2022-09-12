package br.com.lucasdevjava.techSolution.service.impl;


import br.com.lucasdevjava.techSolution.enums.Profile;
import br.com.lucasdevjava.techSolution.model.Admin;
import br.com.lucasdevjava.techSolution.repository.AdminRepository;
import br.com.lucasdevjava.techSolution.repository.RoleRepository;
import br.com.lucasdevjava.techSolution.service.AdminService;
import br.com.lucasdevjava.techSolution.service.UserService;
import br.com.lucasdevjava.techSolution.service.exception.ObjectNotFoundException;
import br.com.lucasdevjava.techSolution.utils.JWTUtil;
import br.com.lucasdevjava.techSolution.utils.UTILS;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

import static java.util.Arrays.asList;


@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminRepository repo;
    @Autowired
    private RoleService roleService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private HttpServletRequest request;



    @Override
    public  Admin save(Admin admin) {
        userService.checkEmailExists(admin.getEmail());
        admin = (Admin)  UTILS.now().encryptPassword(admin);
        var role = roleService.findByName(Profile.ADMIN.getDescription());
        admin.setRole(asList(role));
      return repo.save(admin);
    }

    @Override
    public Admin searchForMe() {
      Integer id = jwtUtil.getIdByRequest(request);
      var admin = this.findById(id);
        return admin;
    }

    @Override
    public Admin findById(Integer id) {
        Optional<Admin> admin = repo.findById(id);
        return admin.orElseThrow(()-> new ObjectNotFoundException("Admin não encontrado"));
    }
}
