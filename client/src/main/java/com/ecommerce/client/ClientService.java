package com.ecommerce.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.feignclients.user.UserClient;
import com.ecommerce.feignclients.user.UserDTO;

import java.io.IOException;
import java.util.List;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private UserClient userClient;
	
	// ------------------------------------ Get all clients ------------------------------------
	public List<Client> getAllClients(){
		return clientRepository.findAll();
	}

	// ------------------------------------ Get client by id ------------------------------------
	public Client getClient(Long id){
		return clientRepository.findById(id).get();
	}
	
	// ------------------------------------ Get client by userId ------------------------------------
		public Client getClientByUserId(Long userId){
			return clientRepository.findClientByUserId(userId).get();
		}
	
	// ------------------------------------ get client by username ------------------------------------
	public Client getClientByUsername(String username) {
        try {
            // Fetch user by username using Feign client
            UserDTO user = userClient.findUserByUsername(username);
            if (user == null) {
                throw new IllegalArgumentException("User not found for username: " + username);
            }

            // Fetch client by user ID
            Long userId = user.getId();
            System.out.println("userId = " + userId);
            return getClientByUserId(userId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while fetching client by username: " + e.getMessage());
        }
    }

	// ------------------------------------ register client ------------------------------------
	public Client registerClient(RegisterClientRequest request) {
		Long userId = request.userId();
		if (userId != null) {
            try {
                // Check if user exists using Feign client
                UserDTO user = userClient.findUserById(userId);
                if (user == null) {
                    throw new IllegalArgumentException("User not found for ID: " + userId);
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Error while checking user existence: " + e.getMessage());
            }
        }
		
		Client client = Client.builder()
				.firstName(request.firstName())
				.lastName(request.lastName())
				.email(request.email())
				.tel(request.tel())
				.userId(request.userId())
				.build();

		// todo: check if email is valid
		// todo: check if email not taken
		// todo: store client in db
		return clientRepository.saveAndFlush(client);
	}

	public Client updateClient(Long id, RegisterClientRequest request) throws IOException {
		Client client = clientRepository.findById(id).get();
		client.setFirstName(request.firstName());
		client.setLastName(request.lastName());
		client.setEmail(request.email());
		client.setTel(request.tel());
		return clientRepository.save(client);
	}

	public void deleteClient(Long id) {
		clientRepository.deleteById(id);
	}
	
}
