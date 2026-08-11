package com.example.LOGITRACK.service;

import com.example.LOGITRACK.dto.request.ClientRequestDTO;
import com.example.LOGITRACK.dto.response.ClientResponseDTO;
import com.example.LOGITRACK.dto.response.ProduitResponseDTO;
import com.example.LOGITRACK.entity.Client;
import com.example.LOGITRACK.mapper.ClientMapper;
import com.example.LOGITRACK.repository.ClientRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepo clientRepo;
    private final ClientMapper mapper;

    public  ClientService(ClientRepo clientRepo, ClientMapper mapper)
    {
        this.clientRepo= clientRepo;
        this.mapper = mapper;
    }


    public ClientResponseDTO addClient(ClientRequestDTO clientRequestDTO) {
   if(clientRepo.existsByEmail(clientRequestDTO.getEmail())){
       throw new RuntimeException("Email already exists");
        }
        Client addClient =mapper.toEntity(clientRequestDTO);
        Client saveClient= clientRepo.save(addClient);
        return mapper.toResponseDTO(saveClient);
    }

    @Transactional
    public Page<ClientResponseDTO> getAllClient(String nom, Pageable pageable){
        if (nom != null && !nom.isEmpty()) {
            return clientRepo.findByNomContainingIgnoreCase(nom, pageable)
                    .map(mapper::toResponseDTO);
        }
        return clientRepo.findAll(pageable)
                .map(mapper::toResponseDTO);
    }


    public ClientResponseDTO getById(Long id){
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return mapper.toResponseDTO(client);
    }
    public void deleteClient(Long id){
        if (!clientRepo.existsById(id)) {
            throw new RuntimeException("Client not found");
        }

        clientRepo.deleteById(id);    }

    public Long getTotalClient(){
        return clientRepo.count();
    }

}
