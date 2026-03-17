package com.example.LOGITRACK.Repository;

import com.example.LOGITRACK.ENTITY.Command;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandRepo extends JpaRepository<Command,Long> {

    List<Command> findByClinetId(Long clientId);
    long coun();
}
