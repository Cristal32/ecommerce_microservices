package com.ecommerce.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.auth.dto.RegisterUserRequest;
import com.ecommerce.auth.dto.UpdateUserRequest;
import com.ecommerce.auth.dto.UserRequest;
import com.ecommerce.auth.model.User;
import com.ecommerce.auth.service.AuthService;
import com.ecommerce.feignclients.customer.ClientDTO;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	// ================================= GET mapping =================================
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = authService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
		User user = authService.findUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/getByUsername/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
		User user = authService.findUserByUsername(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/validateToken")
	public ResponseEntity<?> validateToken(@RequestParam("token") String token) {
		authService.validateToken(token);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// ================================= POST mapping =================================
//	@PostMapping("/register")
//	public User registerUser(@RequestBody User user) {
//		return authService.registerUser(user);
//	}
	
	@PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        User user = registerUserRequest.getUser();
        ClientDTO client = registerUserRequest.getClient();
        User registeredUser = authService.registerUserWithClient(user, client);
        return ResponseEntity.ok(registeredUser);
    }
	
	@PostMapping("/getToken")
	public ResponseEntity<String> getToken(@RequestBody UserRequest user) {
		// Tell the authManager to authenticate the request before giving out the token
		Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		// If authenticated, generate the token
		if(auth.isAuthenticated()) {
			String token = authService.generateToken(user.getUsername());
			return new ResponseEntity<>(token, HttpStatus.OK);
		}else {
			throw new RuntimeException("Invalid user credentials!");
		}
	}
	
	// ================================= PUT mapping =================================
	@PutMapping("/update/{username}")
	public User registerUser(@PathVariable("username") String username, @RequestBody UpdateUserRequest userRequest) {
		return authService.updateUser(username, userRequest);
	}
}
