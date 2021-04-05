package com.test.jpa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.test.jpa.entity.User;
import com.test.jpa.entity.UserRole;

public interface UserRoleService {

    /** Create a new UserRole */
    public ResponseEntity<Object> createUserRole(UserRole userRole);
    
    /** Update an Existing UserRole */
    public ResponseEntity<Object> updateUserRole(UserRole userRole, Long id);
    
    /** Delete an UserRole*/
    public ResponseEntity<Object> deleteUserRole(Long id);
    
    /** Delete All the UserRoles*/
    public List<ResponseEntity<Object>> deleteAllUserRoles();
    
}
























