package com.sample.microservices.batch.controller;
 
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.batch.service.ScheduleTaskService;
import com.sample.microservices.batch.task.RunnableTaskTwo;

@RestController
@RequestMapping("/task-scheduler")
public class TaskSchedulerController {
	
	private final ScheduleTaskService taskService;
	
	TaskSchedulerController(ScheduleTaskService taskService) {
		this.taskService = taskService;		
	}
 
    @GetMapping("/schedule-task/{message}/{oldid}/{newid}/{delaymins}")
    public void scheduleRunnableWithTaskAndDate(@PathVariable("message") String message,
    		@PathVariable("oldid") Long oldid, @PathVariable("newid") Long newid, @PathVariable("delaymins") Integer delayseconds) {
    	    	
    	Calendar calendar = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
    	calendar.add(Calendar.MINUTE, delayseconds);
    	System.out.println("Current Time is  " + new Date());
    	System.out.println("Task will start at time of " + calendar.getTime());
    	
    	this.taskService.scheduleRunnableWithTaskAndDate(message, oldid, newid, calendar.getTime());
    	
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