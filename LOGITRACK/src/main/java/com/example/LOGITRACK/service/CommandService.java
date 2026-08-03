package com.example.LOGITRACK.service;

import com.example.LOGITRACK.dto.response.CommandResponseDTO;
import com.example.LOGITRACK.entity.Client;
import com.example.LOGITRACK.entity.Command;
import com.example.LOGITRACK.enumm.CommandeStatut;
import com.example.LOGITRACK.mapper.CommandMapper;
import com.example.LOGITRACK.repository.ClientRepo;
import com.example.LOGITRACK.repository.CommandRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommandService {

    private final ClientRepo clientRepo;
    private final CommandRepo commandRepo;
    private final CommandMapper mapper;


    public CommandService(ClientRepo clientRepo, CommandRepo commandRepo, CommandMapper mapper) {
        this.clientRepo = clientRepo;
        this.commandRepo = commandRepo;
        this.mapper = mapper;
    }


    public CommandResponseDTO createCommand(Long clientId) {

        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Command command = new Command();

        command.setClient(client);
        command.setDatecommand(LocalDate.now());


        command.setCommandeStatut(CommandeStatut.EN_ATTENTE);

        Command saveCommand= commandRepo.save(command);
        return mapper.toResponseDTO(saveCommand);
    }


    public List<CommandResponseDTO> getAllCommands() {
        return commandRepo.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }


    public CommandResponseDTO getCommandById(Long id) {

        Command command = commandRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Command not found"));

        return mapper.toResponseDTO(command);
    }


    public CommandResponseDTO updateStatusCommand(Long id,
                                                  CommandeStatut statut) {

        Command command = commandRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Command not found"));

        command.setCommandeStatut(statut);

        Command updated = commandRepo.save(command);

        return mapper.toResponseDTO(updated);
    }


    public List<CommandResponseDTO> getCommandByClient(Long clientId) {

        return commandRepo.findByClientId(clientId)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }


    public long totalCommands() {

        return commandRepo.countCommands();
    }
}