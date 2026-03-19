package com.example.LOGITRACK.entity;

import jakarta.persistence.*;

@Entity
public class LigneCommand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private  int quantity;
    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Command commande;
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    public LigneCommand(){}

    public LigneCommand(Long id, int quantity, Command commande, Produit produit) {
        this.id = id;
        this.quantity = quantity;
        this.commande = commande;
        this.produit = produit;
    }
    public LigneCommand( int quantity, Command commande, Produit produit) {
        this.quantity = quantity;
        this.commande = commande;
        this.produit = produit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
