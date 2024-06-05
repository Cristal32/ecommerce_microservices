package com.ecommerce.notification;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDao extends JpaRepository<Notification, Long> {
	Optional<List<Notification>> findByClientId(Long clientId);
}
