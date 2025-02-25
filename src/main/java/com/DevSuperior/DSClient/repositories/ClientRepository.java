package com.DevSuperior.DSClient.repositories;

import com.DevSuperior.DSClient.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
