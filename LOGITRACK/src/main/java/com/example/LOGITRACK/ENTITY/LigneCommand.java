package com.example.LOGITRACK.ENTITY;

import jakarta.persistence.*;

@Entity
public class LigneCommand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private  int quantity;
    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Command command;
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    public LigneCommand(){}

    public LigneCommand(Long id, int quantity, Command command, Produit produit) {
        this.id = id;
        this.quantity = quantity;
        this.command = command;
        this.produit = produit;
    }
    public LigneCommand( int quantity, Command command, Produit produit) {
        this.quantity = quantity;
        this.command = command;
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
