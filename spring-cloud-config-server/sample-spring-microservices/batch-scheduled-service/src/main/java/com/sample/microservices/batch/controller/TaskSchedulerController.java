package com.sample.microservices.batch.controller;
 
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.batch.service.ScheduleTaskService;

@RestController
@RequestMapping("/task-scheduler")
public class TaskSchedulerController {
	
	private final ScheduleTaskService taskService;
	
	TaskSchedulerController(ScheduleTaskService taskService) {
		this.taskService = taskService;		
	}
 
    @GetMapping("/{fixedDelaySeconds}")
    public void scheduleRunnableWithFixedDelay(@PathVariable("fixedDelaySeconds") Integer fixedDelaySeconds) {
    	
    	this.taskService.scheduleRunnableWithFixedDelay(fixedDelaySeconds*1000);
    }
    
    @GetMapping("/scheduled-tasks")
    public List<String> getAllScheduledTasks() {
    	
    	return this.taskService.getAllScheduledTasks();
    }
    
    @DeleteMapping("/{name}")
    public void removeNamedTaskFromScheduler(@PathVariable("name") String name) {
    	this.taskService.removeNamedTaskFromScheduler(name);    	
    }
}