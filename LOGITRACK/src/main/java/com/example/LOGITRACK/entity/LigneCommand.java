package com.example.LOGITRACK.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
@Table(name = "ligne_commande")
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
}
