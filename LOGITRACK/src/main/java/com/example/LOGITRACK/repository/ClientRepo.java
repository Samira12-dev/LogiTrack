package com.example.LOGITRACK.repository;

import com.example.LOGITRACK.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client,Long> {
}
