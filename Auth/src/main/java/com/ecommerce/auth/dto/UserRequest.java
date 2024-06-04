package com.ecommerce.auth.dto;

public class UserRequest {
	private String username;
    private String password;
	
	// No-args constructor
    public UserRequest() {}

    // All-args constructor
    public UserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters

    // username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
