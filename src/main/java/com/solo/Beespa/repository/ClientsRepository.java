package com.solo.Beespa.repository;


import com.solo.Beespa.models.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Clients, Long> {
}
