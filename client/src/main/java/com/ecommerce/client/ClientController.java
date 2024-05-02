package com.ecommerce.client;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/client")
public record ClientController(ClientService clientService) {
	private static final Logger logger = Logger.getLogger(ClientController.class.getName());
	
	@PostMapping
	public void registerClient(@RequestBody ClientRegistrationRequest clientRegistrationRequest) {
		clientService.registerClient(clientRegistrationRequest);
		logger.info("New client registration " + clientRegistrationRequest);
	}
}
