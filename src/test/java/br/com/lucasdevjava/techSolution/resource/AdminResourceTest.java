package br.com.lucasdevjava.techSolution.resource;
import static br.com.lucasdevjava.techSolution.security.SecurityConstants.TOKEN_PREXI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import br.com.lucasdevjava.techSolution.config.AplicationConfingTest;
import br.com.lucasdevjava.techSolution.dto.AdminNewDTO;

import br.com.lucasdevjava.techSolution.enums.Profile;
import br.com.lucasdevjava.techSolution.model.Admin;
import br.com.lucasdevjava.techSolution.model.Role;
import br.com.lucasdevjava.techSolution.repository.AdminRepository;
import br.com.lucasdevjava.techSolution.repository.RoleRepository;
import br.com.lucasdevjava.techSolution.repository.UserRepository;
import br.com.lucasdevjava.techSolution.security.SecurityConstants;
import br.com.lucasdevjava.techSolution.service.AdminService;
import br.com.lucasdevjava.techSolution.service.impl.RoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@DisplayName("AdminResource")
public class AdminResourceTest extends AplicationConfingTest {

    @MockBean
    private AdminRepository adminRepository;
    @MockBean
    private RoleService roleService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleRepository roleRepositoy;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    public void testSalva() throws JsonProcessingException, Exception {

        var admin = new AdminNewDTO("lucasDev@gmail.com","123456","lucas","silva");


        mockMvc.perform(post("/admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(admin))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    public void testExceptionBAD_REQUEST() throws JsonProcessingException, Exception {

        var admin = new AdminNewDTO("lucasEx@gmail.com","123456","lucas","silva");


        mockMvc.perform(post("/admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(admin))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }



    @BeforeEach
    public void setup() {

        var adminException = new Admin("lucasEx@gmail.com","123456","lucas","silva",null);
        Mockito.when(userRepository.findByEmail(adminException.getEmail())).thenReturn(adminException);

        var adminSearchMe = new Admin("lucas@gmail.com","123456","lucas","silva",null);
        adminSearchMe.setId(1);
        Mockito.when(userRepository.findByEmail(adminSearchMe.getEmail())).thenReturn(adminSearchMe);
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(adminSearchMe));

        var role = new Role();
        role.setNomeRole(Profile.ADMIN.getDescription());
        Mockito.when(roleRepositoy.findBynomeRole(Profile.ADMIN.getDescription())).thenReturn(role);
    }

    public String getToken(){

      Role role = new Role();
      role.setNomeRole("ROLE_ADMIN");


        String token = Jwts.builder()
                .setSubject("lucas@gmail.com")
                .claim("id", 1)
                .claim("email", "lucas@gmail.com")
                .claim("role", Arrays.asList(role))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
        System.out.println("token:"+token);
        return "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdWNhc0BnbWFpbC5jb20iLCJpZCI6MSwiZW1haWwiOiJsdWNhc0BnbWFpbC5jb20iLCJyb2xlIjpbeyJub21lUm9sZSI6IlJPTEVfQURNSU4iLCJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE2NjI2ODcyNDYsImV4cCI6MTY2Mjc3MzY0Nn0.0buLyplX2zRJwi-XbY3buYyLCCEvVlrAF_Uloqw8yUnQKGLikCdXMDpEqxRQDMKbisKF5pr7rJUwbEOBaRAYrA";
    }
}
