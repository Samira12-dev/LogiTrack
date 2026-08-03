package com.example.LOGITRACK.mapper;

import com.example.LOGITRACK.dto.request.ClientRequestDTO;
import com.example.LOGITRACK.dto.response.ClientResponseDTO;
import com.example.LOGITRACK.entity.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientRequestDTO dto);

    ClientResponseDTO toResponseDTO(Client client);

    List<ClientResponseDTO> toListDto(List<Client>clients);
}