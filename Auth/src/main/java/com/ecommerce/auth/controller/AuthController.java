package com.ecommerce.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.auth.model.User;
import com.ecommerce.auth.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthService authService;
	
	// ================================= GET mapping =================================
	@GetMapping("/getToken")
	public ResponseEntity<String> getToken(User user) {
		String token = authService.generateToken(user.getUsername());
		return new ResponseEntity<>(token, HttpStatus.OK);
	}
	
	@GetMapping("/validateToken")
	public ResponseEntity<?> validateToken(@RequestParam("token") String token) {
		authService.validateToken(token);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// ================================= POST mapping =================================
	@PostMapping("/register")
	public User registerUser(User user) {
		return authService.registerUser(user);
	}
}
