package com.test.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.jpa.entity.Role;
import com.test.jpa.entity.User;
import com.test.jpa.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	
	List<UserRole> findByUser(User user);
	List<UserRole> findByRole(Role role);

}