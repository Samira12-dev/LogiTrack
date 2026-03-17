package com.example.LOGITRACK.ENTITY;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Client {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String nom;
    private String email;
    private String telephone;
    private String ville;
    @OneToMany(mappedBy = "client")
    private List<Command> commands;

    public  Client(){}

    public Client(Long id, String nom, String email, String telephone, String ville, List<Command> commands) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.ville = ville;
        this.commands = commands;
    }

    public Client( String nom, String email, String telephone, String ville,List<Command> commands) {
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.ville = ville;
        this.commands = commands;
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
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }
}
