package com.example.LOGITRACK.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "client")
public class Client {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String nom;
    private String email;
    private String telephone;
    private String ville;
    @OneToMany(mappedBy = "client")
    private List<Command> commande;

    public  Client(){}

    public Client(Long id, String nom, String email, String telephone, String ville, List<Command> commands) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.ville = ville;
        this.commande = commands;
    }

    public Client( String nom, String email, String telephone, String ville,List<Command> commands) {
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.ville = ville;
        this.commande = commands;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public List<Command> getCommands() {
        return commande;
    }

    public void setCommands(List<Command> commands) {
        this.commande = commands;
    }
}
