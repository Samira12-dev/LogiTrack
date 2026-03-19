package com.example.LOGITRACK.repository;

import com.example.LOGITRACK.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProduitRepo extends JpaRepository<Produit,Long> {

    @Query("SELECT p FROM Produit p WHERE p.category = :category")
    List<Produit> findbyCategory(String category);


    @Query("SELECT p FROM Produit p WHERE p.price < :prix")
    List<Produit> findByPrixLessThan(double prix);

}
