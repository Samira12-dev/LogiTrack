package com.example.LOGITRACK.Repository;

import com.example.LOGITRACK.ENTITY.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepo extends JpaRepository<Produit,Long> {

    List<Produit> findbyCategory(String category);
    List<Produit> findbyprixInferieur(double price);
}
