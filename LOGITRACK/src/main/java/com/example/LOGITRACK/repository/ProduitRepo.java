package com.example.LOGITRACK.repository;

import com.example.LOGITRACK.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ProduitRepo extends JpaRepository<Produit,Long> {


    List<Produit> findByCategoryContainingIgnoreCase(String category);
    List<Produit> findByPriceLessThanEqual(double price);
    List<Produit> findByQuantityStockLessThan(int quantity);
}
