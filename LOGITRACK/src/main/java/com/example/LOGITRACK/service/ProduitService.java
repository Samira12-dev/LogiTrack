package com.example.LOGITRACK.service;


import com.example.LOGITRACK.dto.request.ProduitRequestDTO;
import com.example.LOGITRACK.dto.response.ProduitResponseDTO;
import com.example.LOGITRACK.entity.Produit;
import com.example.LOGITRACK.mapper.ProduitMapper;
import com.example.LOGITRACK.repository.ProduitRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {
    private final ProduitRepo produitRepo;
    private  final ProduitMapper mapper;
    public ProduitService(ProduitRepo produitRepo, ProduitMapper mapper) {
        this.produitRepo = produitRepo;
        this.mapper = mapper;
    }

    public ProduitResponseDTO addProduit(ProduitRequestDTO produitRequestDTO) {
        Produit  produit =mapper.toEntity(produitRequestDTO);
        Produit saved= produitRepo.save(produit);
        return  mapper.toResponse(saved);
    }

    public List<ProduitResponseDTO> getAllProduits(){
        return produitRepo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
    public ProduitResponseDTO getById(Long id){
        Produit produit = produitRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit not found"));

        return mapper.toResponse(produit);
    }

    public void deleteProduit(Long id){
        if (!produitRepo.existsById(id)) {
            throw new RuntimeException("Produit not found");
        }

        produitRepo.deleteById(id);
    }
    public List<ProduitResponseDTO> getProduitByCategory(String category) {

        return produitRepo.findByCategory(category)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }


    public List<ProduitResponseDTO> getProduitByPrixInferieur(double price) {

        return produitRepo.findByPriceLessThan(price)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }


}
