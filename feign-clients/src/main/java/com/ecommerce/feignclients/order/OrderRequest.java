package com.ecommerce.feignclients.order;

public class OrderRequest {
    private Long clientId;
    private String clientEmail;
    private Long productId;
    private Integer amount;

    // Default constructor
    public OrderRequest() {
    }

    // All-args constructor
    public OrderRequest(Long clientId, String clientEmail, Long productId, Integer amount) {
        this.clientId = clientId;
        this.clientEmail = clientEmail;
        this.productId = productId;
        this.amount = amount;
    }

    // Getters and setters
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    // toString method
    @Override
    public String toString() {
        return "OrderRequest{" +
                "clientId=" + clientId +
                ", clientEmail='" + clientEmail + '\'' +
                ", productId=" + productId +
                ", amount=" + amount +
                '}';
    }
}
