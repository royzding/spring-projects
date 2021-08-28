package com.async.test.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.async.test.model.User;
import com.async.test.service.UserService;


@RestController
public class TestAsyncController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestAsyncController.class);
	
	private final UserService userService;
	
	TestAsyncController(UserService userService) {
		this.userService = userService;		
	}
		
	@GetMapping
	public String hello() { 
		return "hello";
	}

	@GetMapping("/hi")
	public String hi() throws InterruptedException, ExecutionException {
		LOGGER.info("Test Async Methods.");
		
		for(int i=0; i<5; i++) {
			System.out.println(this.userService.createAndReturnUser());
			this.userService.createUserWithConcurrentTaskExecutor();
			this.userService.createUserWithDefaultExecutor();
			this.userService.createUserWithThreadPoolTaskExecutor();			
		}
		
	    // Start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        CompletableFuture < User > page1 = this.userService.findUser("PivotalSoftware");
        CompletableFuture < User > page2 = this.userService.findUser("CloudFoundry");
        CompletableFuture < User > page3 = this.userService.findUser("Spring-Projects");
        CompletableFuture < User > page4 = this.userService.findUser("RameshMF");
        // Wait until they are all done
        CompletableFuture.allOf(page1, page2, page3, page4).join();

        // Print results, including elapsed time
        LOGGER.info("Elapsed time: " + (System.currentTimeMillis() - start));
        LOGGER.info("--> " + page1.get());
        LOGGER.info("--> " + page2.get());
        LOGGER.info("--> " + page3.get());
        LOGGER.info("--> " + page4.get());
		
		
		return "Test Async Methods";
	}
	
}