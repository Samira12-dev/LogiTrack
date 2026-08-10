package com.example.LOGITRACK.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.LOGITRACK.entity.Command;
import com.example.LOGITRACK.enumm.CommandeStatut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommandRepo extends JpaRepository<Command,Long> {


    List<Command> findByClientId(Long clientId);
    @Query("SELECT COUNT(c) FROM Command c")
    long countCommands();

    Page<Command> findByCommandeStatut(CommandeStatut statut, Pageable pageable);}
