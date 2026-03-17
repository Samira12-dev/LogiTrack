package com.example.LOGITRACK.Service;

import com.example.LOGITRACK.ENTITY.Client;
import com.example.LOGITRACK.ENTITY.LigneCommand;
import com.example.LOGITRACK.Repository.ClientRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepo clientRepo;
    public ClientService(ClientRepo clientRepo){
        this.clientRepo= clientRepo;
    }

    public Client addClient(Client client){
        return clientRepo.save(client);
    }
    public List<Client> getAllClient(){
        return clientRepo.findAll();
    }
    public Client getById(Long id){
        return clientRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }
    public void deleteClient(Long id){
         clientRepo.deleteById(id);
    }

}
