package br.com.lucasdevjava.techSolution.resource;


import br.com.lucasdevjava.techSolution.dto.ClientNewDTO;
import br.com.lucasdevjava.techSolution.model.Client;
import br.com.lucasdevjava.techSolution.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClientResource {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> save(@Validated @RequestBody ClientNewDTO clientNewDTO){
        var client = modelMapper.map(clientNewDTO, Client.class);
        client = clientService.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

}
