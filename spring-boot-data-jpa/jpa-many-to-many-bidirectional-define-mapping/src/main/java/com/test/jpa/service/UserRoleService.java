package com.test.jpa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.test.jpa.entity.Role;
import com.test.jpa.entity.User;
import com.test.jpa.entity.UserRole;

public interface UserRoleService {

    /** Create a new UserRole */
    ResponseEntity<Object> createUserRole(UserRole userRole);
    
    /** Update an Existing UserRole */
    ResponseEntity<Object> updateUserRole(UserRole userRole, Long id);
    
    /** Delete an UserRole*/
    ResponseEntity<Object> deleteUserRole(Long id);
    
    /** Delete All the UserRoles*/
    List<ResponseEntity<Object>> deleteAllUserRoles();
    
    /** Delete All the UserRoles*/
    List<ResponseEntity<Object>> saveUserRoles(User user, List<Role> roles);
    
}
























