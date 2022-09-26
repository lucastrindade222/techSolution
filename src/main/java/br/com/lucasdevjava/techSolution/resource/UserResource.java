package br.com.lucasdevjava.techSolution.resource;

import br.com.lucasdevjava.techSolution.dto.ProfileDTO;
import br.com.lucasdevjava.techSolution.model.Admin;
import br.com.lucasdevjava.techSolution.model.User;
import br.com.lucasdevjava.techSolution.service.AdminService;
import br.com.lucasdevjava.techSolution.service.ClientService;
import br.com.lucasdevjava.techSolution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserResource {
   @Autowired
    private UserService userService;


    @GetMapping("/searchMe")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> searchForMe(){
        var profile= userService.searchForMe();

        return  ResponseEntity.ok().body(profile);
    }


}
