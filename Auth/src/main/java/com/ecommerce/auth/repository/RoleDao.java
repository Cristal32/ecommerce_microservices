package com.ecommerce.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.auth.model.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
	Optional<Role> findRoleById(Long id);
	Optional<Role> findRoleByName(String name);
	void deleteRoleById(Long id);
}
