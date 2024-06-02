package com.ecommerce.feignclients.order;

import java.time.LocalDateTime;

import com.ecommerce.feignclients.customer.ClientDTO;
import com.ecommerce.feignclients.product.ProductResponse;

public class OrderResponse {
    private Long id;
    private ClientDTO client;
    private ProductResponse product;
    private LocalDateTime createdAt;

    // Default constructor
    public OrderResponse() {}

    // All-args constructor
    public OrderResponse(Long id, ClientDTO client, ProductResponse product, LocalDateTime createdAt) {
        this.id = id;
        this.client = client;
        this.product = product;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public ProductResponse getProduct() {
        return product;
    }

    public void setProduct(ProductResponse product) {
        this.product = product;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // toString method
    @Override
    public String toString() {
        return "OrderResponse{" +
                "id=" + id +
                ", client=" + client.getLastName() +
                ", product=" + product.getName() +
                ", createdAt=" + createdAt +
                '}';
    }
}