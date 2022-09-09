package br.com.lucasdevjava.techSolution.utils;



import br.com.lucasdevjava.techSolution.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UTILS {

    public User encryptPassword(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            var encryptPassword = encoder.encode(user.getPassword());
            user.setPassword(encryptPassword);


        return user;
    }

    public static UTILS now(){
        return  new UTILS();
    }
}
