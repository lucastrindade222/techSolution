package br.com.lucasdevjava.techSolution.resource;

import br.com.lucasdevjava.techSolution.config.AplicationConfingTest;
import br.com.lucasdevjava.techSolution.dto.ClientNewDTO;
import br.com.lucasdevjava.techSolution.enums.Profile;
import br.com.lucasdevjava.techSolution.model.Client;
import br.com.lucasdevjava.techSolution.model.Role;
import br.com.lucasdevjava.techSolution.repository.ClientRepository;
import br.com.lucasdevjava.techSolution.repository.RoleRepository;
import br.com.lucasdevjava.techSolution.repository.UserRepository;
import br.com.lucasdevjava.techSolution.service.ClientService;
import br.com.lucasdevjava.techSolution.service.impl.RoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@DisplayName("ClientResource")
public class ClientResourceTest extends AplicationConfingTest {

    @MockBean
    private ClientRepository clientRepository;
    @MockBean
    private RoleService roleService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleRepository roleRepositoy;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testSalva() throws JsonProcessingException, Exception {

        var clientNewDTO = new ClientNewDTO("lucasDev@gmail.com","123456","lucas","silva");


        mockMvc.perform(post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientNewDTO))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }
   @Test
    public void testExceptionBAD_REQUEST() throws JsonProcessingException, Exception {

        var clientNewDTO = new ClientNewDTO("lucasEx@gmail.com","123456","lucas","silva");


        mockMvc.perform(post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientNewDTO))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
    @BeforeEach
    public void setup() {

        var client = new Client("lucasEx@gmail.com","123456","lucas","silva",null);


        Mockito.when(userRepository.findByEmail(client.getEmail())).thenReturn(client);


        var role = new Role();
        role.setNomeRole(Profile.CLIENT.getDescription());
        Mockito.when(roleRepositoy.findBynomeRole(Profile.CLIENT.getDescription())).thenReturn(role);
    }


}
