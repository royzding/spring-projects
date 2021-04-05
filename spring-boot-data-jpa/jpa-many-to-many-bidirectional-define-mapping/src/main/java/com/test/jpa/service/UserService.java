package com.test.jpa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.test.jpa.entity.User;

public interface UserService {

    /** 
     * Create a new user  
	 *
     *@param user
     */
    @Transactional
    public ResponseEntity<Object> createUser(User user);
    
    /** 
     * Delete a specified user given the id 
     * 
     * @param id
     */
    public ResponseEntity<Object> deleteUser(Long id);
    
    /** 
     * Update a user 
     * 
     * @param id
     * @param user
     * 
     */
    public ResponseEntity<Object> updateUser(Long id, User user);
    
    /** 
     * Delete All the users
     * 
     * @return
     * 
     */
    public List<ResponseEntity<Object>> deleteAllUsers();
}









