package com.ecommerce.feignclients.user;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {
	private Long id;
	private String username;
    private String password;
    private Set<RoleDTO> roles;
	
	// No-args constructor
    public UserDTO() {}

    // All-args constructor
    public UserDTO(Long id, String username, String password, Set<RoleDTO> roles) {
    	this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    // Getters and setters
    
 // username
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


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
    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }
    
 // Builder pattern
    public static class Builder {
        private Long id;
        private String username;
        private String password;
        private Set<RoleDTO> roles = new HashSet<>();

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

        public Builder roles(Set<RoleDTO> roles) {
            this.roles = roles;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(id, username, password, roles);
        }
    }
}
