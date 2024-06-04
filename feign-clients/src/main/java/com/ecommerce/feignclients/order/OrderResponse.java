package com.ecommerce.feignclients.order;

import java.time.LocalDateTime;

import com.ecommerce.feignclients.customer.ClientDTO;
import com.ecommerce.feignclients.product.ProductDTO;

public class OrderResponse {
    private Long id;
    private ClientDTO client;
    private ProductDTO product;
    private LocalDateTime createdAt;

    // Default constructor
    public OrderResponse() {}

    // All-args constructor
    public OrderResponse(Long id, ClientDTO client, ProductDTO product, LocalDateTime createdAt) {
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

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
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