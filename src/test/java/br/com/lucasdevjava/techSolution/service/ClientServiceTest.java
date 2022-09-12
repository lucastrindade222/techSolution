package br.com.lucasdevjava.techSolution.service;


import br.com.lucasdevjava.techSolution.config.AplicationConfingTest;
import br.com.lucasdevjava.techSolution.enums.Profile;
import br.com.lucasdevjava.techSolution.model.Client;
import br.com.lucasdevjava.techSolution.model.Role;
import br.com.lucasdevjava.techSolution.repository.ClientRepository;
import br.com.lucasdevjava.techSolution.repository.RoleRepository;
import br.com.lucasdevjava.techSolution.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;


@DisplayName("ClientServiceImpl")
public class ClientServiceTest extends AplicationConfingTest {

    @Value("${api.profiles.images.profile}")
    private String url;
 ;
    @MockBean
    private ClientRepository clientRepositoy;
    @MockBean
    private RoleRepository roleRepositoy;
    @Autowired
    private ClientService clientService;




    @Test
    public void save(){
        var cliente = new Client(  "lucasDevJava@email.com", "password123","lucas","Lucasjava",url);
        cliente = clientService.save(cliente);

    }


    @BeforeEach
    public void setup() {


        var role = new Role();
        role.setNomeRole(Profile.CLIENT.getDescription());
        Mockito.when(roleRepositoy.findBynomeRole(Profile.CLIENT.getDescription())).thenReturn(role);
    }


}

