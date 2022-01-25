package com.sample.microservices.batch.controller;
 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.batch.service.RetryableTaskService;

@RestController
@RequestMapping("/retryable-task")
public class RetryableTaskController {
	
	private final RetryableTaskService taskService;
	
	RetryableTaskController(RetryableTaskService taskService) {
		this.taskService = taskService;		
	}
		
    @GetMapping("/recover")
    public void executeTaskWithRecover() {
    	
    	this.taskService.executeTaskWithRecover();
    }


    @GetMapping("/success/{times}")
    public Integer executeTaskWithSuccess(@PathVariable("times") Integer times) {
    	
    	return this.taskService.executeTaskWithSuccess(times);
    }

    @GetMapping("/exception/{times}")
    public void executeTaskWithException(@PathVariable("times") Integer times) {
    	
    	this.taskService.executeTaskWithException(times);
    }

    @GetMapping("/count")
    public void resetCounter() {
    	
    	this.taskService.resetCounter();
    }

}