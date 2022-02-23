package com.sample.microservices.batch.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sample.microservices.batch.data.model.Person;
import com.sample.microservices.batch.service.ScheduleTaskService;

@Component
public class MyApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyApplicationListener.class);
    
    
	private final ScheduleTaskService taskService;
	
	public MyApplicationListener(ScheduleTaskService taskService) {
		this.taskService = taskService;		
	}
	

	  
    @Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
    	
    	LOGGER.info("ApplicationListener#onApplicationEvent()");

    	List<Person> list = this.taskService.findAll();
    	
    	Long oldId = list.get(0).getId();
    	Long newId = list.get(1).getId();
    	
    	list.forEach(System.out::println);
    	
    	Calendar calendar = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
    	calendar.add(Calendar.MINUTE, 3);
    	System.out.println("ids are  " + oldId + ":" + newId);
    	System.out.println("Current Time is  " + new Date());
    	System.out.println("Task will start at time of " + calendar.getTime());
    	
    	this.taskService.scheduleRunnableWithTaskAndDate("MyApplicationListener", oldId, newId, calendar.getTime());    	
    	
	}

	
}