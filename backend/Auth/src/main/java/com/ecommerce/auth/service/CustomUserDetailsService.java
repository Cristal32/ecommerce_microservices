package com.ecommerce.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ecommerce.auth.dto.CustomUserDetails;
import com.ecommerce.auth.model.User;
import com.ecommerce.auth.repository.UserDao;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user =  userDao.findByUsername(username);
		return user.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("User not found with username" + username));
	}
	
}
