package com.ecommerce.auth.dto;

import java.util.Set;

import com.ecommerce.auth.model.Role;

public record UpdateUserRequest(
		String username,
	    String password,
	    Set<Role> roles) {}