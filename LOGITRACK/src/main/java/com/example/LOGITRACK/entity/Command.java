package com.example.LOGITRACK.entity;

import com.example.LOGITRACK.enumm.CommandeStatut;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "commande")
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datecommand;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "commande")
    private List<LigneCommand> lignes;

    @Column(name = "command_statut")
    @Enumerated(EnumType.STRING)
    private CommandeStatut commandeStatut;

    public Command() {
    }

    public Command(Long id, LocalDate datecommand, CommandeStatut commandeStatut,
                   Client client, List<LigneCommand> lignes) {
        this.id = id;
        this.datecommand = datecommand;
        this.commandeStatut = commandeStatut;
        this.client = client;
        this.lignes = lignes;
    }

    public Command(LocalDate datecommand, CommandeStatut commandeStatut,
                   Client client, List<LigneCommand> lignes) {
        this.datecommand = datecommand;
        this.commandeStatut = commandeStatut;
        this.client = client;
        this.lignes = lignes;
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

    public CommandeStatut getCommandeStatut() {
        return commandeStatut;
    }

    public void setCommandeStatut(CommandeStatut commandeStatut) {
        this.commandeStatut = commandeStatut;
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