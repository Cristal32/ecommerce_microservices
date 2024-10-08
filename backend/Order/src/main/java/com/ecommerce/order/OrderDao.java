package com.ecommerce.order;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {
	Optional<Order> findOrderById(Long orderId);
	Optional<List<Order>> findOrdersByClientId(Long clientId);
	void deleteById(Long orderId);
}