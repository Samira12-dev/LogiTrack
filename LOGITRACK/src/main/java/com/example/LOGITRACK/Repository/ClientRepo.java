package com.example.LOGITRACK.Repository;

import com.example.LOGITRACK.ENTITY.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client,Long> {
}
