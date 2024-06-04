package com.ecommerce.client;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
	Optional<Client> findClientByUserId(Long userId);
}
