package br.com.lucasdevjava.techSolution.resource;


import br.com.lucasdevjava.techSolution.dto.AdminNewDTO;
import br.com.lucasdevjava.techSolution.model.Admin;
import br.com.lucasdevjava.techSolution.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminResource {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<Admin> save(@Validated @RequestBody AdminNewDTO adminNewDTO){
      var admin = modelMapper.map(adminNewDTO, Admin.class);
      admin = adminService.save(admin);
      return ResponseEntity.status(HttpStatus.CREATED).body(admin);
    }
  @GetMapping("/teste")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public  ResponseEntity<String> test(){
        return  ResponseEntity.ok().body("Tudo ok aqui");
  }


}

