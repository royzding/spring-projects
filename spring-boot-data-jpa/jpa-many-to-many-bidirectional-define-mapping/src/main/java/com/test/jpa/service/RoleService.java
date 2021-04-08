package com.test.jpa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.test.jpa.entity.Role;

public interface RoleService {

    /** 
     * Create a new role  
	 *
     *@param role
     */
    @Transactional
    ResponseEntity<Object> addRole(Role role);
    
    Role createRole(Role role);
    
    /** 
     * Delete a specified role given the id 
     * 
     * @param id
     */
    ResponseEntity<Object> deleteRole(Long id);
    
    /** 
     * Update a Role 
     * 
     * @param id
     * @param role
     * 
     */
    ResponseEntity<Object> updateRole(Long id, Role role);
    
    /** 
     * Delete All the Roles
     * 
     * @return
     * 
     */
    List<ResponseEntity<Object>> deleteAllRoles();
    
    /** 
     * Check if the role exists
     * 
     * @param role
     * 
     * @return
     * 
     */
    Role checkRoles(Role role);
}









