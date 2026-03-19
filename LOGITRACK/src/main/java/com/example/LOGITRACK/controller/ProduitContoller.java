package com.example.LOGITRACK.controller;

import com.example.LOGITRACK.entity.Produit;
import com.example.LOGITRACK.service.ProduitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produit")
public class ProduitContoller {
     private  final ProduitService produitService;

     public ProduitContoller(ProduitService produitService){
         this.produitService= produitService;
     }

     @PostMapping
    public Produit addProduit(@RequestBody Produit produit){
         return  produitService.addProduit(produit);
     }
     @GetMapping
    public List<Produit> getAllProducts(){
         return produitService.getAllProduits();
     }
     @GetMapping("/{id}")
    public Produit getProduit(@PathVariable Long id){
         return produitService.getById(id);
     }
     @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable Long id){
          produitService.deleteProduit(id);
     }

     @GetMapping("/category/{categorie}")
    public List<Produit> getProduitByCategory(String categorie){
         return produitService.getProduitByCategory(categorie);
     }

     @GetMapping("/price/prix")
    public List<Produit> getByInferieurPrix( double prix){
         return produitService.getProduitByPrixInferiuer(prix);
     }
}
