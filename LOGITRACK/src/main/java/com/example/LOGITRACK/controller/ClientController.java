package com.example.LOGITRACK.controller;

import com.example.LOGITRACK.entity.Client;
import com.example.LOGITRACK.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;
    public ClientController(ClientService clientService){
        this.clientService=clientService;
    }

    @PostMapping
    public Client addClient(@RequestBody Client client){
        return  clientService.addClient(client);
    }
    @GetMapping
    public List<Client> getAllClients(){
        return  clientService.getAllClient();
    }
    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id){
        return clientService.getById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }
}
