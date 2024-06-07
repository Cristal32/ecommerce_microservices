package com.ecommerce.client;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String tel;
    private Long userId;
    
    // Constructors
    public Client() {}

    public Client(Long id, String firstName, String lastName, String email, String tel, Long userId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.tel = tel;
        this.userId = userId;
    }

    // Getters & setters
    // id
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id=id;
    }

    // firstName
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }

    // lastName
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName=lastName;
    }

    // email
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email=email;
    }

    // tel
    public String getTel(){ return tel;}

    public void setTel(String tel){ this.tel = tel;}
    
    // userId
    public Long getUserId() { return userId; }
    
    public void setUserId(Long userId) { this.userId = userId; }

    // toString() method
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
    
 // Builder pattern static method
    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String tel;
        private Long userId;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder tel(String tel){
            this.tel = tel;
            return this;
        }
        
        public Builder userId(Long userId) {
        	this.userId = userId;
        	return this;
        }

        public Client build() {
            return new Client(id, firstName, lastName, email, tel, userId);
        }
    }
}


