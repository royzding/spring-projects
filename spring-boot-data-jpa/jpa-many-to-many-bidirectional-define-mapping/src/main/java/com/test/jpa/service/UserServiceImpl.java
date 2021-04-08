package com.test.jpa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.jpa.entity.Role;
import com.test.jpa.entity.User;
import com.test.jpa.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final RoleService roleService;
    
    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService,
    		RoleService roleService) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.roleService = roleService;
    }
    
    /** Create a new User */
    @Override
    @Transactional
    public ResponseEntity<Object> createUser(User model) {
        User user = new User();
        if (userRepository.findByEmail(model.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("The Email is already Present, Failed to Create new User");
        } else {
            user.setFirstName(model.getFirstName());
            user.setLastName(model.getLastName());
            user.setMobile(model.getMobile());
            user.setEmail(model.getEmail());
            
            List<Role> roles = model.getRoles();
            
            //roles.forEach(r->r=roleRepository.findByName(r.getName()).orElse(r));
            
            user.setRoles(null);

            User savedUser = userRepository.save(user);         
            
            Optional<User> newUser = userRepository.findById(savedUser.getId());
            if (newUser.isPresent()) {
            	
            	roles.forEach(r->roleService.checkRoles(r));
            	
            	userRoleService.saveUserRoles(newUser.get(), roles);
            	return ResponseEntity.ok("User Created Successfully");
            } else {
            	return ResponseEntity.unprocessableEntity().body("Failed Creating User as Specified");
            }
        }
    }

    /** Update an Existing User */
    @Transactional
    @Override
    public ResponseEntity<Object> updateUser(Long id, User user) {
        if(userRepository.findById(id).isPresent()) {
            User newUser = userRepository.findById(id).get();
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setMobile(user.getMobile());
            newUser.setEmail(user.getEmail());
            newUser.setRoles(user.getRoles());
            User savedUser = userRepository.save(newUser);
            if(userRepository.findById(savedUser.getId()).isPresent())
                return  ResponseEntity.accepted().body("User updated successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed updating the user specified");
        } else return ResponseEntity.unprocessableEntity().body("Cannot find the user specified");
    }
    /** Delete an User*/
    @Override
    public ResponseEntity<Object> deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            if (userRepository.findById(id).isPresent())
                return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified User");
            else return ResponseEntity.ok().body("Successfully deleted the specified user");
        } else return ResponseEntity.badRequest().body("Cannot find the user specified");
    }
    
    /** Delete All the Users*/
    @Override
    public List<ResponseEntity<Object>> deleteAllUsers() {
    	
    	return userRepository.findAll().stream().map(u->deleteUser(u.getId())).collect(Collectors.toList());
    }

}
























