package com.test.jpa.controller;

import com.test.jpa.entity.Role;
import com.test.jpa.repository.RoleRepository;
import com.test.jpa.service.RoleService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;
    private final RoleRepository roleRepository;

    public RoleController(RoleService roleService, RoleRepository roleRepository) {
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/all")
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        if(roleRepository.findById(id).isPresent())
            return roleRepository.findById(id).get();
        else return null;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createRole(@RequestBody Role role) {
        return  roleService.addRole(role);
    }
    
    @DeleteMapping("delete/all")
    public List<ResponseEntity<Object>> deleteAllRoles() {
        return roleService.deleteAllRoles();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable Long id) {
        return roleService.deleteRole(id);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable Long id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }


}




