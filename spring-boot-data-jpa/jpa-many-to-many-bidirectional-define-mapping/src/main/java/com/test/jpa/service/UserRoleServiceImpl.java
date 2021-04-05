package com.test.jpa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.test.jpa.entity.UserRole;
import com.test.jpa.entity.UserRole;
import com.test.jpa.repository.RoleRepository;
import com.test.jpa.repository.UserRoleRepository;

public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleRepository userRoleRepository;
    private RoleRepository roleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }
    
    /** Create a new UserRole */
    @Override
    public ResponseEntity<Object> createUserRole(UserRole userRole) {
    	
    	UserRole ur = userRoleRepository.save(userRole);
    	
        if (userRoleRepository.findById(ur.getId()).isPresent())
            return ResponseEntity.ok("UserRole Created Successfully");
        else return ResponseEntity.unprocessableEntity().body("Failed Creating UserRole as Specified");
    }
   	
    /** Update an Existing UserRole */
    @Transactional
    @Override
    public ResponseEntity<Object> updateUserRole(UserRole userRole, Long id) {
        if(userRoleRepository.findById(id).isPresent()) {
            UserRole newUserRole = userRoleRepository.findById(id).get();
            newUserRole.setUser(userRole.getUser());
            newUserRole.setRole(userRole.getRole());
            newUserRole.setUserRoleType(userRole.getUserRoleType());
            
            UserRole savedUserRole = userRoleRepository.save(newUserRole);
            if(userRoleRepository.findById(savedUserRole.getId()).isPresent())
                return  ResponseEntity.accepted().body("UserRole updated successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed updating the userRole specified");
        } else return ResponseEntity.unprocessableEntity().body("Cannot find the userRole specified");
    }
    /** Delete an UserRole*/
    @Override
    public ResponseEntity<Object> deleteUserRole(Long id) {
        if (userRoleRepository.findById(id).isPresent()) {
            userRoleRepository.deleteById(id);
            if (userRoleRepository.findById(id).isPresent())
                return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified UserRole");
            else return ResponseEntity.ok().body("Successfully deleted the specified userRole");
        } else return ResponseEntity.badRequest().body("Cannot find the userRole specified");
    }
    
    /** Delete All the Users*/
    @Override
    public List<ResponseEntity<Object>> deleteAllUserRoles() {
    	
    	return userRoleRepository.findAll().stream().map(u->deleteUserRole(u.getId())).collect(Collectors.toList());
    }

}





































