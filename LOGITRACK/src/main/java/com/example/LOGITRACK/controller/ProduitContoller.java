package com.example.LOGITRACK.controller;

import com.example.LOGITRACK.dto.request.ProduitRequestDTO;
import com.example.LOGITRACK.dto.response.ProduitResponseDTO;
import com.example.LOGITRACK.entity.Produit;
import com.example.LOGITRACK.service.ProduitService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produit")
public class ProduitContoller {
     private  final ProduitService produitService;

     public ProduitContoller(ProduitService produitService){
         this.produitService= produitService;
     }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
     @PostMapping
    public ProduitResponseDTO addProduit(@Valid @RequestBody ProduitRequestDTO produit){
         return  produitService.addProduit(produit);
     }
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
     @GetMapping
    public List<ProduitResponseDTO> getAllProducts(){
         return produitService.getAllProduits();
     }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
     @GetMapping("/{id}")
    public ProduitResponseDTO getProduit(@PathVariable Long id){
         return produitService.getById(id);
     }

    @PreAuthorize("hasRole('ADMIN')")
     @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable Long id){
          produitService.deleteProduit(id);
     }


    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
     @GetMapping("/category/{categorie}")
    public List<ProduitResponseDTO> getProduitByCategory( @PathVariable String category){
         return produitService.getProduitByCategory(category);
     }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/price")
    public List<ProduitResponseDTO> getByInferieurPrix(
            @RequestParam double prix){

        return produitService.getProduitByPrixInferieur(prix);
    }
}
