package com.ecommerce.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	

	public List<Client> getAllClients(){
		return clientRepository.findAll();
	}

	public Client getClient(Integer id){
		return clientRepository.findById(id).get();
	}

	public Client registerClient(RegisterClientRequest request) {
		Client client = Client.builder()
				.firstName(request.firstName())
				.lastName(request.lastName())
				.email(request.email())
				.build();

		// todo: check if email is valid
		// todo: check if email not taken
		// todo: store client in db
		return clientRepository.saveAndFlush(client);
	}

	public Client updateClient(Integer id, UpdateClientRequest request) throws IOException {
		Client client = clientRepository.findById(id).get();
		client.setFirstName(request.firstName());
		client.setLastName(request.lastName());
		client.setEmail(request.email());
		return clientRepository.save(client);
	}

	public void deleteClient(Integer id) {
		clientRepository.deleteById(id);
	}
	
}
