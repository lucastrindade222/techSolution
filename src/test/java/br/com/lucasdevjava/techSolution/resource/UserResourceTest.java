package br.com.lucasdevjava.techSolution.resource;

import br.com.lucasdevjava.techSolution.config.AplicationConfingTest;
import br.com.lucasdevjava.techSolution.model.Admin;
import br.com.lucasdevjava.techSolution.model.Role;
import br.com.lucasdevjava.techSolution.repository.UserRepository;
import br.com.lucasdevjava.techSolution.security.SecurityConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@DisplayName("UserResource")
public class UserResourceTest extends AplicationConfingTest {
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    public void searchForMeTest() throws Exception {



        mockMvc.perform(post("/user/searchMe")

                )
                .andExpect(MockMvcResultMatchers.status().isOk());

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
    @BeforeEach
    public void setup() {

        var adminException = new Admin("lucasEx@gmail.com", "123456", "lucas", "silva", null);
        Mockito.when(userRepository.findByEmail(adminException.getEmail())).thenReturn(adminException);
    }

}
