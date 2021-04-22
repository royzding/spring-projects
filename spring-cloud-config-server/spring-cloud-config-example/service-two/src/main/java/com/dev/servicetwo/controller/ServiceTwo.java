package com.dev.servicetwo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("servicetwo")
public class ServiceTwo {

    @GetMapping("/welcome")
    public String welcome() {
    	return "Welcome Service Two";
    }

}