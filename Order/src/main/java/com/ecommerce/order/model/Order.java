package com.ecommerce.order.model;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clientId;
    private Long productId;
    private LocalDateTime date;

    public Order(){}

    public Order(Long id, Long clientId, Long productId, LocalDateTime date){
        this.id=id;
        this.date=date;
        this.clientId = clientId;
        this.productId = productId;
    }

    // Getters & setters ---------------------------------------
    // id
    public Long getId(){return this.id;}
    public void setId(Long id){this.id = id;}
    
    // clientId
    public void setClientId(Long clientId){this.clientId = clientId;}
    public Long getClientId(){return this.clientId;}
    
    // productId
    public void setProductId(Long productId){this.productId = productId;}
    public Long getProductId(){return this.productId;}

    // date
    public LocalDateTime getDate(){return this.date;}
    public void setDate(LocalDateTime date){this.date = date;}

    // ---------------------------------------
    
    // toString method
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", client id='" + clientId + '\'' +
                ", product id='" + productId + '\'' +
                '}';
    }

    // Builder pattern static method
    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    public static class Builder {
        private Long id;
        private Long clientId;
        private Long productId;
        private LocalDateTime date;

        private Builder() {}

        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder clientId(Long clientId) {
            this.clientId = clientId;
            return this;
        }
        
        public Builder productId(Long productId) {
            this.productId = productId;
            return this;
        }

        public Builder date(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Order build() {
            return new Order(id, clientId, productId, date);
        }
    }

}