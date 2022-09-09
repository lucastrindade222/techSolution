package br.com.lucasdevjava.techSolution.resource;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import br.com.lucasdevjava.techSolution.config.AplicationConfingTest;
import br.com.lucasdevjava.techSolution.dto.AdminNewDTO;
import br.com.lucasdevjava.techSolution.model.Role;
import br.com.lucasdevjava.techSolution.repository.AdminRepository;
import br.com.lucasdevjava.techSolution.repository.RoleRepository;
import br.com.lucasdevjava.techSolution.service.AdminService;
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

@DisplayName("AdminResource")
public class AdminResourceTest extends AplicationConfingTest {
    @MockBean
    private AdminRepository adminRepository;
    @MockBean
    private RoleService roleService;
    @MockBean
    private RoleRepository roleRepositoy;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSalva() throws JsonProcessingException, Exception {

        var admin = new AdminNewDTO("lucasDev@gmail.com","123456","lucas","silva");


        mockMvc.perform(post("/admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(admin))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @BeforeEach
    public void setup() {


        var role = new Role();
        role.setNameRole(ROLE_ADMIN.name());
        Mockito.when(roleRepositoy.findBynameRole(ROLE_CLIENTE.name())).thenReturn(role);
    }
}
