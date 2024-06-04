package com.ecommerce.auth.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.auth.dto.UpdateUserRequest;
import com.ecommerce.auth.model.User;
import com.ecommerce.auth.repository.UserDao;
import com.ecommerce.feignclients.customer.ClientDTO;
import com.ecommerce.feignclients.customer.CustomerClient;

@Service
public class AuthService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private CustomerClient customerClient;
	
	@Autowired
	private JwtService jwtService;
	
	// ---------------------- get all users --------------------------
	public List<User> getAllUsers(){
		return userDao.findAll();
	}
	
	// ---------------------- get a user by their id --------------------------
		public User findUserById(Long id){
			return userDao.findById(id).orElse(null);
		}
	
	// ---------------------- save a user --------------------------
//	public User registerUser(User user) {
//		user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt the password
//		return userDao.save(user);
//	}
	
		public User registerUserWithClient(User user, ClientDTO client) {
		    User savedUser = registerUser(user);

		    // Log the user ID and client details
		    System.out.println("User saved with ID: " + savedUser.getId());

		    // Create client
		    client.setUserId(savedUser.getId());
		    System.out.println("Client details: " + client);
		    registerClient(client);

		    return savedUser;
		}
	
	@Transactional
	public User registerUser(User user) {
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    return userDao.save(user);
	}
	
	@Transactional
	public void registerClient(ClientDTO client) {
	    System.out.println("Client details: " + client);
	    customerClient.createClient(client);
	}
	
	// ---------------------- update a user --------------------------
		public User updateUser(String username, UpdateUserRequest userRequest) {
			Optional<User> existingUser = userDao.findByUsername(username);
			
			if (existingUser.isPresent()) {
	        	
	            User user = existingUser.get();
	            if (userRequest.username() != null) { 
	            	user.setUsername(userRequest.username());}
	            
	            if (userRequest.password() != null) { 
	            	user.setPassword(passwordEncoder.encode(userRequest.password()));
	            }
	            if (userRequest.roles() != null) {
	                user.setRoles(userRequest.roles());
	            }
	            return userDao.saveAndFlush(user);
	        } else {
	            throw new EntityNotFoundException("User with username " + username + " not found");
	        }
		}
	
	// ---------------------- generate a token --------------------------
//	public String generateToken(String username) {
//		return jwtService.generateToken(username);
//	}
	
	public String generateToken(String username) {
        User user = userDao.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return jwtService.generateToken(username, user.getRoles());
    }
	
	// ---------------------- validate the token --------------------------
	public void validateToken(String token) {
		jwtService.validateToken(token);
	}
}
