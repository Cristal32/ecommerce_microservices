package com.ecommerce.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.auth.model.User;
import com.ecommerce.auth.repository.UserDao;

@Service
public class AuthService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	// ---------------------- save a user --------------------------
	public User registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt the password
		return userDao.save(user);
	}
	
	// ---------------------- generate a token --------------------------
	public String generateToken(String username) {
		return jwtService.generateToken(username);
	}
	
	// ---------------------- validate the token --------------------------
	public void validateToken(String token) {
		jwtService.validateToken(token);
	}
}
