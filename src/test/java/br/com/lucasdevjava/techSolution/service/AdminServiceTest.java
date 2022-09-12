package br.com.lucasdevjava.techSolution.service;

import br.com.lucasdevjava.techSolution.config.AplicationConfingTest;
import br.com.lucasdevjava.techSolution.enums.Profile;
import br.com.lucasdevjava.techSolution.model.Admin;
import br.com.lucasdevjava.techSolution.model.Role;
import br.com.lucasdevjava.techSolution.repository.AdminRepository;
import br.com.lucasdevjava.techSolution.repository.RoleRepository;
import br.com.lucasdevjava.techSolution.service.impl.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

@DisplayName("AdminServiceImpl")
public class AdminServiceTest extends AplicationConfingTest {


    @Value("${api.profiles.images.profile}")
    private String url;

    @MockBean
    private AdminRepository adminRepository;
    @MockBean
    private RoleService roleService;
    @MockBean
    private RoleRepository roleRepositoy;
    @Autowired
    private AdminService adminService;

    @Test
    public void save(){

        var admin = new Admin("admin@email.com","123456","admin","teste",url);

        adminService.save(admin);


    }


    @BeforeEach
    public void setup() {


        var role = new Role();
        role.setNomeRole(Profile.ADMIN.getDescription());
        Mockito.when(roleRepositoy.findBynomeRole(Profile.ADMIN.getDescription())).thenReturn(role);
    }
}
