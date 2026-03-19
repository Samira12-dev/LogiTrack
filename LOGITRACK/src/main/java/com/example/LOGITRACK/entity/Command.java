package com.example.LOGITRACK.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "commande")
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate datecommand;
    private String status;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToMany(mappedBy = "commande")
    private List<LigneCommand> lignes;



    public Command(){}

    public Command(Long id, LocalDate datecommand, String status,Client client,List<LigneCommand> lignes) {
        this.id = id;
        this.datecommand = datecommand;
        this.status = status;
        this.client = client;
        this.lignes = lignes;
    }
    public Command( LocalDate datecommand, String status,Client client,List<LigneCommand> lignes) {
        this.datecommand = datecommand;
        this.status = status;
        this.lignes = lignes;
        this.client=client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatecommand() {
        return datecommand;
    }

    public void setDatecommand(LocalDate datecommand) {
        this.datecommand = datecommand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<LigneCommand> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneCommand> lignes) {
        this.lignes = lignes;
    }
}

