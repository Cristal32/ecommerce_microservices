package com.ecommerce.auth.dto;

import com.ecommerce.auth.model.User;
import com.ecommerce.feignclients.customer.ClientDTO;

public class RegisterUserRequest {
	private User user;
    private ClientDTO client;

    // Getters and setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }
}
