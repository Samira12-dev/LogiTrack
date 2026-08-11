package com.example.LOGITRACK.controller;

import com.example.LOGITRACK.dto.request.LigneCommandRequestDTO;
import com.example.LOGITRACK.dto.response.CommandResponseDTO;
import com.example.LOGITRACK.entity.Command;
import com.example.LOGITRACK.enumm.CommandeStatut;
import com.example.LOGITRACK.service.ClientService;
import com.example.LOGITRACK.service.CommandService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commands")
public class CommandController {
    private final CommandService commandService;
    private final ClientService clientService;

    public CommandController(CommandService commandService,ClientService clientService){
        this.clientService=clientService;
        this.commandService= commandService;
    }


    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping
    public CommandResponseDTO CreateCommand(@Valid @RequestParam Long clientId){
        return commandService.createCommand(clientId);
    }


    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    @GetMapping
    public Page<CommandResponseDTO> getAllCommands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) CommandeStatut statut,
            @RequestParam(defaultValue = "id") String orderBy,
            @RequestParam(defaultValue = "asc") String order){

        Sort sort = order.equalsIgnoreCase("asc")
                ? Sort.by(orderBy).ascending()
                : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        if(statut!=null){
            return commandService.getCommandsByStatus(statut,pageable);
        }

        return commandService.getAllCommands(pageable);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    @GetMapping("/{id}")
    public CommandResponseDTO getCommand(@PathVariable  Long id){
        return commandService.getCommandById(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    @PutMapping("/{id}/status")
    public CommandResponseDTO updateCommand(@PathVariable Long id, @RequestParam CommandeStatut status){
        return commandService.updateStatusCommand(id,status);
    }


    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/client/{clientId}")
    public List<CommandResponseDTO> getCommandById(@PathVariable Long clientId){
        return commandService.getCommandByClient(clientId);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/count")
    public long totalCommand(){
        return commandService.totalCommands();
    }
    @PostMapping("/{id}/produit")
    public CommandResponseDTO addProduit(@PathVariable Long id,@RequestBody LigneCommandRequestDTO dto){
        dto.setCommandeId(id);
        return commandService.addProduit(dto);
    }

    @GetMapping("/count/pending")
    public long countPending(){
        return commandService.countByStatus(CommandeStatut.EN_ATTENTE);
    }

    @GetMapping("/count/shipped")
    public long countShipped(){
        return commandService.countByStatus(CommandeStatut.EXPEDIEE);
    }

    @GetMapping("/count/delivered")
    public long countDelivered(){
        return commandService.countByStatus(CommandeStatut.LIVREE);
    }
}
