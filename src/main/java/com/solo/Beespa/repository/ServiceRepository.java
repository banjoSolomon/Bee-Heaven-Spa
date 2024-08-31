package com.solo.Beespa.repository;


import com.solo.Beespa.models.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Services, Long> {
}
