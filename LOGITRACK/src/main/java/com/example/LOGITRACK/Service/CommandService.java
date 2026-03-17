package com.example.LOGITRACK.Service;

import com.example.LOGITRACK.ENTITY.Client;
import com.example.LOGITRACK.ENTITY.Command;
import com.example.LOGITRACK.Repository.ClientRepo;
import com.example.LOGITRACK.Repository.CommandRepo;
import com.example.LOGITRACK.Repository.ProduitRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommandService {

    private final ClientRepo clientRepo;
    private  final CommandRepo commandRepo;
    public CommandService(ClientRepo clientRepo, CommandRepo commandRepo){
        this.clientRepo=clientRepo;
        this.commandRepo=commandRepo;
    }

    public Command createCommand( Long clientId) {
        Client client = clientRepo.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        Command command = new Command();
        command.setClient(client);
        command.setDatecommand(LocalDate.now());
        command.setStatus("EN_Attente");
        return commandRepo.save(command);
    }
    public List<Command> getAllCommands(){
        return commandRepo.findAll();
    }
    public Command getCommandById(Long id){
        return  commandRepo.findById(id).orElseThrow(()-> new RuntimeException(" command not found"));
    }

    public Command updateStatusCommand(Long id ,String status){
        Command command = getCommandById(id);
        command.setStatus(status);
        return  commandRepo.save(command);
    }
    public List<Command>  getCommandByClient(Long clientId){
        return  commandRepo.findByClinetId(clientId);
    }
    public  long totalCommands(){
        return commandRepo.coun();
    }
}
