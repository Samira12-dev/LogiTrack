package com.example.LOGITRACK.service;

import com.example.LOGITRACK.dto.request.LigneCommandRequestDTO;
import com.example.LOGITRACK.dto.response.CommandResponseDTO;
import com.example.LOGITRACK.entity.Client;
import com.example.LOGITRACK.entity.Command;
import com.example.LOGITRACK.entity.LigneCommand;
import com.example.LOGITRACK.entity.Produit;
import com.example.LOGITRACK.enumm.CommandeStatut;
import com.example.LOGITRACK.mapper.CommandMapper;
import com.example.LOGITRACK.repository.ClientRepo;
import com.example.LOGITRACK.repository.CommandRepo;
import com.example.LOGITRACK.repository.LigneCommandRepo;
import com.example.LOGITRACK.repository.ProduitRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommandService {

    private final ClientRepo clientRepo;
    private final CommandRepo commandRepo;
    private final CommandMapper mapper;
    private  final ProduitRepo produitRepo;
    private final LigneCommandRepo ligneCommandRepo;

    public CommandService(ClientRepo clientRepo, CommandRepo commandRepo, CommandMapper mapper, ProduitRepo produitRepo, LigneCommandRepo ligneCommandRepo) {
        this.clientRepo = clientRepo;
        this.commandRepo = commandRepo;
        this.mapper = mapper;
        this.produitRepo = produitRepo;
        this.ligneCommandRepo = ligneCommandRepo;
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


    public Page<CommandResponseDTO> getAllCommands(Pageable pageable) {
        return commandRepo.findAll(pageable)
                .map(mapper::toResponseDTO);
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
    public CommandResponseDTO addProduit(LigneCommandRequestDTO dto){
        Command command=commandRepo.findById(dto.getCommandeId()).orElseThrow();
        Produit produit=produitRepo.findById(dto.getProduitId()).orElseThrow();

        LigneCommand ligne=new LigneCommand();
        ligne.setCommande(command);
        ligne.setProduit(produit);
        ligne.setQuantity(dto.getQuantity());

        ligneCommandRepo.save(ligne);

        return mapper.toResponseDTO(command);
    }

    public Page<CommandResponseDTO> getCommandsByStatus(CommandeStatut statut,Pageable pageable){
        return commandRepo.findByCommandeStatut(statut,pageable)
                .map(mapper::toResponseDTO);
    }
}