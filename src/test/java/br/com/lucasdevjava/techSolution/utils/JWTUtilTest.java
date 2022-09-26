package br.com.lucasdevjava.techSolution.utils;

import br.com.lucasdevjava.techSolution.config.AplicationConfingTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("JWTUtil")
public class JWTUtilTest extends AplicationConfingTest {

    @Autowired
    private  JWTUtil jWTUtil;

    @Test
    public  void fromJsonToRoles(){
        String json = "[{nomeRole=ROLE_ADMIN, authority=ROLE_ADMIN},{nomeRole=ROLE_Cliente, authority=ROLE_Cliente}]";


       var roles = jWTUtil.fromJsonToRoles(json);
        Assertions.assertThat(roles.get(0).getNomeRole()).isEqualTo("ROLE_ADMIN");
    }
    @Test
    public  void  getProfile(){
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdWNhc2RldmphdmFAZ21haWwuY29tIiwiaWQiOjEsImVtYWlsIjoibHVjYXNkZXZqYXZhQGdtYWlsLmNvbSIsInJvbGUiOlt7Im5vbWVSb2xlIjoiUk9MRV9BRE1JTiIsImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlhdCI6MTY2NDE5NTU4OSwiZXhwIjoxNjY0MjgxOTg5fQ.mBSJ9eff6GHwGgQJ3qIIaUHRx47c0PaOa_FUlX_vRf9dgqIESe3XZyWDR93i8K_lnb0LJMXnFVOoqa8QTbVN2w";


        var id = jWTUtil.getProfile(token);
        Assertions.assertThat(id).isEqualTo( "[{nomeRole=ROLE_ADMIN, authority=ROLE_ADMIN}]");
    }
    @Test
    public  void  getIdByTokenTest(){
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdWNhc2RldmphdmFAZ21haWwuY29tIiwiaWQiOjEsImVtYWlsIjoibHVjYXNkZXZqYXZhQGdtYWlsLmNvbSIsInJvbGUiOlt7Im5vbWVSb2xlIjoiUk9MRV9BRE1JTiIsImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlhdCI6MTY2NDE5NTU4OSwiZXhwIjoxNjY0MjgxOTg5fQ.mBSJ9eff6GHwGgQJ3qIIaUHRx47c0PaOa_FUlX_vRf9dgqIESe3XZyWDR93i8K_lnb0LJMXnFVOoqa8QTbVN2w";


        var id = jWTUtil.getIdByToken(token);
        Assertions.assertThat(id).isEqualTo(1);
    }
    @Test
    public  void  getEmailFromSub(){
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdWNhc2RldmphdmFAZ21haWwuY29tIiwiaWQiOjEsImVtYWlsIjoibHVjYXNkZXZqYXZhQGdtYWlsLmNvbSIsInJvbGUiOlt7Im5vbWVSb2xlIjoiUk9MRV9BRE1JTiIsImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlhdCI6MTY2NDE5NTU4OSwiZXhwIjoxNjY0MjgxOTg5fQ.mBSJ9eff6GHwGgQJ3qIIaUHRx47c0PaOa_FUlX_vRf9dgqIESe3XZyWDR93i8K_lnb0LJMXnFVOoqa8QTbVN2w";


        var email = jWTUtil.getEmailFromSub(token);
        Assertions.assertThat(email).isEqualTo("lucasdevjava@gmail.com");
    }

}
