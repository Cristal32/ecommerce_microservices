package com.ecommerce.client;

public class ClientService {
	public void registerClient(ClientRegistrationRequest request) {
		Client client = Client.builder()
				.firstName(request.firstName())
				.lastName(request.lastName())
				.email(request.email())
				.build();
	}
	
	// todo: check if email is valid
	// todo: check if email not taken
	// todo: store client in db
}
