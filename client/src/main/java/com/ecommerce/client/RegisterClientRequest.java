package com.ecommerce.client;

public record RegisterClientRequest(
		String firstName, 
		String lastName, 
		String email,
		String tel) {
}
