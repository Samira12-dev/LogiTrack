package com.example.LOGITRACK.controller;

import com.example.LOGITRACK.dto.request.ProduitRequestDTO;
import com.example.LOGITRACK.dto.response.ProduitResponseDTO;
import com.example.LOGITRACK.entity.Produit;
import com.example.LOGITRACK.service.ProduitService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public Page<ProduitResponseDTO> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {

        Sort sort = order.equalsIgnoreCase("asc")
                ? Sort.by(orderBy).ascending()
                : Sort.by(orderBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return produitService.getAllProduits(pageable);
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
     @GetMapping("/category")
    public List<ProduitResponseDTO> getProduitByCategory( @RequestParam String category){
         return produitService.getProduitByCategory(category);
     }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/price")
    public List<ProduitResponseDTO> getByInferieurPrix(
            @RequestParam double prix){

        return produitService.getProduitByPrixInferieur(prix);
    }

    @GetMapping("/low-stock")
    public List<ProduitResponseDTO> getLowStock() {
        return produitService.getLowStock();
    }
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    @GetMapping("/count")
    public Long getTotalProduit(){
        return produitService.getTotalProduit();
    }
}
