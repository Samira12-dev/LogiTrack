package com.example.LOGITRACK.repository;

import com.example.LOGITRACK.entity.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommandRepo extends JpaRepository<Command,Long> {


    List<Command> findByClientId(Long clientId);
    @Query("SELECT COUNT(c) FROM Command c")
    long countCommands();
}
