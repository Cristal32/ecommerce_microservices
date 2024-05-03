package com.ecommerce.order;

import org.springframework.stereotype.Service;

@Service
public record OrderService(OrderRepository orderRepository) {
    public void registerOrder(OrderRegistrationRequest request) {
        Order order = Order.builder()
                .date(request.date())
                .adresse(request.adresse())
                .statut(request.statut())
                .build();

        // todo: check if email is valid
        // todo: check if email not taken
        // todo: store client in db
        orderRepository.save(order);
    }
}
