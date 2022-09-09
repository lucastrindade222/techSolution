package br.com.lucasdevjava.techSolution.service.impl;


import br.com.lucasdevjava.techSolution.enums.Profile;
import br.com.lucasdevjava.techSolution.model.Admin;
import br.com.lucasdevjava.techSolution.repository.AdminRepository;
import br.com.lucasdevjava.techSolution.repository.RoleRepository;
import br.com.lucasdevjava.techSolution.service.AdminService;
import br.com.lucasdevjava.techSolution.service.UserService;
import br.com.lucasdevjava.techSolution.utils.UTILS;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminRepository repo;
    @Autowired
    private RoleRepository roleRepositoy;


    @Override
    public  Admin save(Admin admin) {
        userService.checkEmailExists(admin.getEmail());
        admin = (Admin)  UTILS.now().encryptPassword(admin);
        admin.setRole(Arrays.asList(roleRepositoy.findBynameRole(Profile.ADMIN.getDescription())));
      return repo.save(admin);
    }
}
