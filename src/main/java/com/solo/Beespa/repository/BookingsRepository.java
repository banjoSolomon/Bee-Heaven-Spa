package com.solo.Beespa.repository;

import com.solo.Beespa.models.Bookings;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {
    List<Bookings> findAllByClientId(Long clientId);

}
