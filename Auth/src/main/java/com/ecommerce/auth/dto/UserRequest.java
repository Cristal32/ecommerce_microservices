package com.ecommerce.auth.dto;

import java.util.HashSet;
import java.util.Set;

import com.ecommerce.auth.model.Role;
import com.ecommerce.auth.model.User;

public class UserRequest {
	private String username;
    private String password;
    private Set<Role> roles;
	
	// No-args constructor
    public UserRequest() {}

    // All-args constructor
    public UserRequest(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
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
    
    // roles
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
 // Builder pattern
    public static class Builder {
        private Long id;
        private String username;
        private String password;
        private Set<Role> roles = new HashSet<>();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder roles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public Builder addRole(Role role) {
            this.roles.add(role);
            return this;
        }

        public User build() {
            return new User(id, username, password, roles);
        }
    }
}
