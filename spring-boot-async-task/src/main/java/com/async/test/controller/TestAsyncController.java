package com.async.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.async.test.service.UserService;


@RestController
public class TestAsyncController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestAsyncController.class);
	
	private final UserService userService;
	
	TestAsyncController(UserService userService) {
		this.userService = userService;		
	}
		
	@GetMapping("/hi")
	public String hi() {
		LOGGER.info("Test Async Methods.");
		
		for(int i=0; i<5; i++) {
			this.userService.createAndReturnUser();
			this.userService.createUserWithConcurrentExecutor();
			this.userService.createUserWithDefaultExecutor();
			this.userService.createUserWithThreadPoolExecutor();			
		}
		
		return "Test Async Methods";
	}
	
}