package com.example.LOGITRACK.service;

import com.example.LOGITRACK.client.NotificationClient;
import com.example.LOGITRACK.dto.request.LigneCommandRequestDTO;
import com.example.LOGITRACK.dto.request.NotificationRequestDTO;
import com.example.LOGITRACK.dto.response.CommandResponseDTO;
import com.example.LOGITRACK.dto.response.NotificationResponseDTO;
import com.example.LOGITRACK.entity.Client;
import com.example.LOGITRACK.entity.Command;
import com.example.LOGITRACK.entity.LigneCommand;
import com.example.LOGITRACK.entity.Produit;
import com.example.LOGITRACK.enumm.CommandeStatut;
import com.example.LOGITRACK.enumm.NotificationType;
import com.example.LOGITRACK.exception.CommandNotFoundException;
import com.example.LOGITRACK.exception.NotificationServiceException;
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

    private final NotificationClient notificationClient;

    public CommandService(ClientRepo clientRepo, CommandRepo commandRepo, CommandMapper mapper, ProduitRepo produitRepo, LigneCommandRepo ligneCommandRepo, NotificationClient notificationClient) {
        this.clientRepo = clientRepo;
        this.commandRepo = commandRepo;
        this.mapper = mapper;
        this.produitRepo = produitRepo;
        this.ligneCommandRepo = ligneCommandRepo;
        this.notificationClient = notificationClient;
    }


    public CommandResponseDTO createCommand(Long clientId) {

        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Command command = new Command();

        command.setClient(client);
        command.setDatecommand(LocalDate.now());


        command.setCommandeStatut(CommandeStatut.EN_ATTENTE);

        Command saveCommand= commandRepo.save(command);

        NotificationRequestDTO notification =  new NotificationRequestDTO();

        notification.setMessage("Order "+saveCommand.getId()+ " create successfully" );
        notification.setType(NotificationType.ORDER_CREATED);
        notification.setOrderId(saveCommand.getId());

        try {
            NotificationResponseDTO response =
                    notificationClient.createNotification(notification);
            if (response == null) {
                throw new NotificationServiceException(
                        "Invalid response from Notification Service"
                );
            }
        } catch (NotificationServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new NotificationServiceException(
                    "Error communicating with Notification Service"
            );
        }

        return mapper.toResponseDTO(saveCommand);
    }


    public Page<CommandResponseDTO> getAllCommands(Pageable pageable) {
        return commandRepo.findAll(pageable)
                .map(mapper::toResponseDTO);
    }


    public CommandResponseDTO getCommandById(Long id) {

        Command command = commandRepo.findById(id)
                .orElseThrow(() -> new CommandNotFoundException(" Command not found"));

        return mapper.toResponseDTO(command);
    }


    public CommandResponseDTO updateStatusCommand(Long id,
                                                  CommandeStatut statut) {

        Command command = commandRepo.findById(id)
                .orElseThrow(() -> new CommandNotFoundException(" Command not found"));

        command.setCommandeStatut(statut);

        Command updated = commandRepo.save(command);
        if(statut == CommandeStatut.EXPEDIEE){
            NotificationRequestDTO notification= new NotificationRequestDTO();
            notification.setMessage("Order "+ updated.getId()+ " has been shipped");
            notification.setType(NotificationType.ORDER_SHIPPED);
            notification.setOrderId(updated.getId());
            try {
                NotificationResponseDTO response =
                        notificationClient.createNotification(notification);
                if (response == null) {
                    throw new NotificationServiceException(
                            "Invalid response from Notification Service"
                    );
                }
            } catch (NotificationServiceException e) {
                throw e;
            } catch (Exception e) {
                throw new NotificationServiceException(
                        "Error communicating with Notification Service"
                );
            }
        }
        if(statut == CommandeStatut.LIVREE){
            NotificationRequestDTO notification= new NotificationRequestDTO();
            notification.setMessage("Order "+ updated.getId()+ " has been delivred");
            notification.setType(NotificationType.ORDER_DELIVERED);
            notification.setOrderId(updated.getId());
            try {
                NotificationResponseDTO response =
                        notificationClient.createNotification(notification);
                if (response == null) {
                    throw new NotificationServiceException(
                            "Invalid response from Notification Service"
                    );
                }
            } catch (NotificationServiceException e) {
                throw e;
            } catch (Exception e) {
                throw new NotificationServiceException(
                        "Error communicating with Notification Service"
                );
            }
        }
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

    public long countByStatus(CommandeStatut status){
        return commandRepo.countByCommandeStatut(status);
    }
}