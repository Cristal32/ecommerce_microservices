package com.ecommerce.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.auth.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

}
