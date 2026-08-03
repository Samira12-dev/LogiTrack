package com.example.LOGITRACK.mapper;

import com.example.LOGITRACK.dto.request.ProduitRequestDTO;
import com.example.LOGITRACK.dto.response.ProduitResponseDTO;
import com.example.LOGITRACK.entity.Produit;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProduitMapper {

    Produit toEntity (ProduitRequestDTO produitRequestDTO);
    List<ProduitResponseDTO> toListDto(List<Produit> produits);
    ProduitResponseDTO toResponse (Produit produit);
}
