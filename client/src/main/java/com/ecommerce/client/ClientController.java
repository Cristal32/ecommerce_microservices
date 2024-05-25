package com.ecommerce.client;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	private static final Logger logger = Logger.getLogger(ClientController.class.getName());

	// ============================= GET mapping =============================	
	@GetMapping("all")
	public List<Client> getAllClients() {return clientService.getAllClients();}

	@GetMapping("client/{id}")
	public Client getClient(@PathVariable("id") Integer id) {return clientService.getClient(id);}

	// ============================= POST mapping =============================	
	@PostMapping("new")
	public Client registerClient(@RequestBody RegisterClientRequest registerClientRequest) throws IOException {
        return clientService.registerClient(registerClientRequest);
    }
	
	// ============================= PUT mapping =============================	
	@PutMapping("update/{id}")
	public Client updateClient(@PathVariable("id") Integer id, @RequestBody UpdateClientRequest updateClientRequest) {
		try {
			return clientService.updateClient(id, updateClientRequest);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// ============================= DELETE mapping =============================
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteClient(@PathVariable("id") Integer id) {
		clientService.deleteClient(id);
		return ResponseEntity.ok("Client deleted successfully!");
	}


}
