package com.sample.microservices.batch.service;

import java.util.Date;
import java.util.List;

import com.sample.microservices.batch.data.model.Person;

public interface ScheduleTaskService {

	List<Person> findAll();
    void scheduleRunnableWithDate(Date date);	
    void scheduleRunnableWithTaskAndDate(String message, Long oldid, Long newid, Date date);	
    void scheduleRunnableWithFixedDelay(Integer fixedDelay);
    void scheduleRunnableWithDateAndFixedDelay(Date date, Integer fixedDelay);
    void scheduleRunnableWithDateAtFixedRate(Date date, Integer fixedRate);
    void scheduleRunnableAtFixedRate(Integer fixedRate);
    void scheduleRunnableWithCronTrigger();
    void scheduleRunnableWithPeriodicTrigger();
    
	// Schedule Task to be executed every night at 00 or 12 am
	void addTaskToScheduler(int id, Runnable task);
	
	// Remove named scheduled task
	void removeNamedTaskFromScheduler(String name);
	
	// Remove scheduled task 
	void removeTaskFromScheduler(int id);
	
	List<String> getAllScheduledTasks();

}
