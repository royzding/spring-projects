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
    public ResponseEntity<Object> addRole(Role role);
    
    /** 
     * Delete a specified role given the id 
     * 
     * @param id
     */
    public ResponseEntity<Object> deleteRole(Long id);
    
    /** 
     * Update a Role 
     * 
     * @param id
     * @param role
     * 
     */
    public ResponseEntity<Object> updateRole(Long id, Role role);
    
    /** 
     * Delete All the Roles
     * 
     * @return
     * 
     */
    public List<ResponseEntity<Object>> deleteAllRoles();
}









