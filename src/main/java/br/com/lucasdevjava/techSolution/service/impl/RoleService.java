package br.com.lucasdevjava.techSolution.service.impl;



import br.com.lucasdevjava.techSolution.model.Role;
import br.com.lucasdevjava.techSolution.repository.RoleRepository;
import br.com.lucasdevjava.techSolution.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepositoy;

   public Role findByName(String name){

      Role role = roleRepositoy.findBynomeRole(name);

      if(role == null){
          throw new ObjectNotFoundException("não encontrado");
      }

  return role;


   }


    public Role findById(Integer id) {
        Optional<Role> role = roleRepositoy.findById(id);
        return role.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " ));
    }




}
