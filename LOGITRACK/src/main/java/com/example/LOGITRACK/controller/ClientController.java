package com.example.LOGITRACK.controller;

import com.example.LOGITRACK.dto.request.ClientRequestDTO;
import com.example.LOGITRACK.dto.response.ClientResponseDTO;
import com.example.LOGITRACK.entity.Client;
import com.example.LOGITRACK.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;
    public ClientController(ClientService clientService){
        this.clientService=clientService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping
    public ClientResponseDTO addClient(@Valid  @RequestBody ClientRequestDTO clientRequestDTO){
        return  clientService.addClient(clientRequestDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    @GetMapping
    public Page<ClientResponseDTO> getAllClients(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size,
                                                 @RequestParam(required = false) String nom,
                                                 @RequestParam(defaultValue = "id") String orderBy,
                                                 @RequestParam(defaultValue = "asc") String order){
        Sort sort = order.equalsIgnoreCase("asc")
                ? Sort.by(orderBy).ascending()
                : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return clientService.getAllClient(nom, pageable);
    }


    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/{id}")
    public ClientResponseDTO getClient(@PathVariable Long id){
        return clientService.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    @GetMapping("/count")
    public Long getTotalClient(){
        return clientService.getTotalClient();
    }
}
