package com.example.LOGITRACK.repository;

import com.example.LOGITRACK.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client,Long> {
    boolean existsByEmail(String email);
    Page<Client> findByNomContainingIgnoreCase(String nom, Pageable pageable);
}
