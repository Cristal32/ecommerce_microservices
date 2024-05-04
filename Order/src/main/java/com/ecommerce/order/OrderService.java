package com.ecommerce.order;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public record OrderService(OrderRepository orderRepository) {

    public List<Order> getAllOrders(){return orderRepository.findAll();}

    public Order getOrder(Integer id){return orderRepository.findById(id).get();}



    public Order registerOrder(RegisterOrderRequest request) {
        Order order = Order.builder()
                .date(request.date())
                .adresse(request.adresse())
                .statut(request.statut())
                .build();

        // todo: check if email is valid
        // todo: check if email not taken
        // todo: store client in db
        return orderRepository.saveAndFlush(order);
    }

    public Order updateOrder(Integer id, UpdateOrderRequest request) throws IOException {
        Order order = orderRepository.findById(id).get();
        order.setDate(request.date());
        order.setAdresse(request.adresse());
        order.setStatut(request.statut());
        return orderRepository.save(order);
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}