package com.dev.serviceone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("serviceone")
public class ServiceOne {

    @GetMapping("/welcome")
    public String welcome() {
    	return "Welcome Service One";
    }

}