package com.example.LOGITRACK.mapper;

import com.example.LOGITRACK.dto.request.LigneCommandRequestDTO;
import com.example.LOGITRACK.dto.response.CommandResponseDTO;
import com.example.LOGITRACK.dto.response.LigneCommandResponseDTO;
import com.example.LOGITRACK.dto.response.ProduitResponseDTO;
import com.example.LOGITRACK.entity.Command;
import com.example.LOGITRACK.entity.LigneCommand;
import com.example.LOGITRACK.entity.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LigneCommandMapper {

    @Mapping(source = "commandeId", target = "commande.id")
    @Mapping(source = "produitId", target = "produit.id")
    LigneCommand toEntity(LigneCommandRequestDTO dto);

    @Mapping(source = "commande.id", target = "commandeId")
    @Mapping(source = "produit.id", target = "produitId")
    LigneCommandResponseDTO toResponseDTO(LigneCommand ligneCommand);

    List<LigneCommandResponseDTO> toListDto(List<LigneCommand> ligneCommands);
}