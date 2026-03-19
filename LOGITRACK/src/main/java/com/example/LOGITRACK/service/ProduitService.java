package com.example.LOGITRACK.service;


import com.example.LOGITRACK.entity.Produit;
import com.example.LOGITRACK.repository.ProduitRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {
    private final ProduitRepo produitRepo;

    public ProduitService(ProduitRepo produitRepo) {
        this.produitRepo = produitRepo;
    }

    public Produit addProduit(Produit produit) {
        return produitRepo.save(produit);
    }

    public List<Produit> getAllProduits(){
        return produitRepo.findAll();
    }
    public Produit getById(Long id){
        return produitRepo.findById(id).orElseThrow(()->new RuntimeException("produit not exist"));
    }

    public void deleteProduit(Long id){
         produitRepo.deleteById(id);
    }
    public List<Produit> getProduitByCategory(String category){
        return produitRepo.findbyCategory(category);
    }
    public List<Produit> getProduitByPrixInferiuer(double price){
        return produitRepo.findByPrixLessThan(price);
    }
}
