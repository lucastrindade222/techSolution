package br.com.lucasdevjava.techSolution.service.impl;




import br.com.lucasdevjava.techSolution.enums.Profile;
import br.com.lucasdevjava.techSolution.model.Admin;
import br.com.lucasdevjava.techSolution.model.Role;
import br.com.lucasdevjava.techSolution.repository.AdminRepository;
import br.com.lucasdevjava.techSolution.repository.RoleRepository;
import br.com.lucasdevjava.techSolution.repository.UserRepository;
import br.com.lucasdevjava.techSolution.service.AdminService;
import br.com.lucasdevjava.techSolution.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.stereotype.Service;

import static java.lang.System.out;


@Service
public class DBServiceImpl implements DBService {
    @Value("${api.profiles.images.profile}")
    private String image;

    @Autowired
      RoleRepository roleRepositoy;
    @Autowired
      AdminService adminService;

    @Autowired
      UserRepository userRepositoy;
    @Autowired
      AdminRepository adminRepositoy;




    @Override
    public void instantiateDatabase() {

        if (roleRepositoy.count() < 1) {
       this.createRole();
        }

        if (adminRepositoy.count()< 1){
      this.createUser();
        }







    }


    @Override
    public void createRole() {
        out.println("-Create role....");

        for (var profile:Profile.values()) {
            Role role = new Role();
            role.setNomeRole(profile.getDescription());
            roleRepositoy.save(role);
            out.println("\n Create "+profile.getDescription());
        }

        out.println("\nCreate end role-");
    }

    @Override
    public void createUser() {
       out.println("-Create user....");

        var admin =new  Admin("lucasdevjava@gmail.com","123456","lucas","java",image);
        adminService.save(admin);

       out.println("\nCreate end user-");

    }


}
