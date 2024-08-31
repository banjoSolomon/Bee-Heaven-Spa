package com.solo.Beespa.repository;

import com.solo.Beespa.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
