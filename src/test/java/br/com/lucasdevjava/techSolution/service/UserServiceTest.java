package br.com.lucasdevjava.techSolution.service;


import br.com.lucasdevjava.techSolution.config.AplicationConfingTest;
import br.com.lucasdevjava.techSolution.model.Admin;
import br.com.lucasdevjava.techSolution.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

@DisplayName("UserServiceImpl")
public class UserServiceTest  extends AplicationConfingTest {
    @Value("${api.profiles.images.profile}")
    private String url;
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void checkEmailExistsTest(){



        try {
            userService.checkEmailExists("lucasEx@email.com");
            Assertions.fail("ERRO o email não existe no sistema");
        }catch (Exception exception){
            Assertions.assertEquals(exception.getMessage(),"O e-mail já existe no sistema.");
        }


    }








    @BeforeEach
    public void setup() {
        var user = new Admin("lucasEx@email.com", "password123","name","surname",url);
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);



    }

}
