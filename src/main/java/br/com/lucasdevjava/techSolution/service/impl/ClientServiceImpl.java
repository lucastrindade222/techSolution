package  br.com.lucasdevjava.techSolution.service.impl;


import br.com.lucasdevjava.techSolution.enums.Profile;
import br.com.lucasdevjava.techSolution.model.Client;
import br.com.lucasdevjava.techSolution.repository.ClientRepository;
import br.com.lucasdevjava.techSolution.service.ClientService;
import br.com.lucasdevjava.techSolution.service.UserService;
import br.com.lucasdevjava.techSolution.utils.UTILS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static java.util.Arrays.asList;


@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public Client findById(Integer id) {
        return null;
    }
    @Override
    public Client save(Client client) {
        userService.checkEmailExists(client.getEmail());

        client = (Client)  UTILS.now().encryptPassword(client);
        var role=   roleService.findByName(Profile.CLIENT.getDescription());
        client.setRole(asList(role));
        return clientRepository.save(client);
    }


}
