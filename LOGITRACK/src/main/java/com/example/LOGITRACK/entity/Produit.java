package com.example.LOGITRACK.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "produit")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String nom;
    private String category;
    private double price;
    private  int quantityStock;
    @OneToMany(mappedBy = "produit")
    private List<LigneCommand>ligneCommandList;


    public Produit(){}

    public Produit(Long id, String nom, String category, double price, int quantityStock,
                   List<LigneCommand> ligneCommandList) {
        this.id = id;
        this.nom = nom;
        this.category = category;
        this.price = price;
        this.quantityStock = quantityStock;
        this.ligneCommandList = ligneCommandList;
    }
    public Produit( String nom, String category, double price, int quantityStock,
                   List<LigneCommand> ligneCommandList) {
        this.nom = nom;
        this.category = category;
        this.price = price;
        this.quantityStock = quantityStock;
        this.ligneCommandList = ligneCommandList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(int quantityStock) {
        this.quantityStock = quantityStock;
    }

    public List<LigneCommand> getLigneCommandList() {
        return ligneCommandList;
    }

    public void setLigneCommandList(List<LigneCommand> ligneCommandList) {
        this.ligneCommandList = ligneCommandList;
    }
}
