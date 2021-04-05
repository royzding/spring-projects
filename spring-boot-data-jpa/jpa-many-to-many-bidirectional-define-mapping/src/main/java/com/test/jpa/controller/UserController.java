package com.test.jpa.controller;

import com.test.jpa.entity.User;
import com.test.jpa.repository.UserRepository;
import com.test.jpa.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
    
    @GetMapping("/all")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        if(userRepository.findById(id).isPresent())
            return userRepository.findById(id).get();
        else return  null;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    
    @DeleteMapping("delete/all")
    public List<ResponseEntity<Object>> deleteAllUsers() {
        return userService.deleteAllUsers();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

}
