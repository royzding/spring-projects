package com.sample.microservices.batch.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;

import com.sample.microservices.batch.data.model.Person;
import com.sample.microservices.batch.repository.PersonRepository;
import com.sample.microservices.batch.task.RunnableTask;
import com.sample.microservices.batch.task.RunnableTaskTwo;

@Service
public class ScheduleTaskServiceImpl implements ScheduleTaskService {

	// Task Scheduler	
	private final TaskScheduler taskScheduler;
	
	private final ThreadPoolTaskScheduler tpTaskscheduler;
	
    @Autowired
    private final CronTrigger cronTrigger;

    @Autowired
    private final PeriodicTrigger periodicTrigger;
	
	// A map for keeping scheduled tasks
	Map<Integer, ScheduledFuture<?>> jobsMap = new HashMap<>();
	
	Map<String, ScheduledFuture<?>> namedJobsMap = new HashMap<>();
	
    private final PersonRepository personRepository;
	
	public ScheduleTaskServiceImpl(TaskScheduler taskScheduler, ThreadPoolTaskScheduler tpTaskscheduler,
			CronTrigger cronTrigger,PeriodicTrigger periodicTrigger, PersonRepository personRepository) {
		this.taskScheduler = taskScheduler;
		this.tpTaskscheduler = tpTaskscheduler;
		this.cronTrigger = cronTrigger;
		this.periodicTrigger = periodicTrigger;
		this.personRepository = personRepository;
	}
	
	@Override
    public void scheduleRunnableWithDate(Date date) {
        tpTaskscheduler.schedule(new RunnableTask("Current Date"), date);
    }
	
	@Override
    public void scheduleRunnableWithFixedDelay(Integer fixedDelay) {
		
		RunnableTask rt = new RunnableTask("Fixed " + fixedDelay/1000 + " seconds Delay");
		ScheduledFuture<?> scheduledTask = tpTaskscheduler.scheduleWithFixedDelay(rt, fixedDelay);
	
		namedJobsMap.put("FixedDelay-" + rt.getName() + "-" + fixedDelay, scheduledTask);
        
    }
	
	@Override
    public void scheduleRunnableWithDateAndFixedDelay(Date date, Integer fixedDelay) {
        tpTaskscheduler.scheduleWithFixedDelay(new RunnableTask("Current Date Fixed 1 second Delay"), date, fixedDelay);
    }
	
	@Override
    public void scheduleRunnableAtFixedRate(Integer fixedRate) {
        tpTaskscheduler.scheduleAtFixedRate(new RunnableTask("Fixed Rate of 2 seconds"), fixedRate);
    }
	
	@Override
    public void scheduleRunnableWithDateAtFixedRate(Date date, Integer fixedRate) {
        tpTaskscheduler.scheduleAtFixedRate(new RunnableTask("Fixed Rate of 2 seconds"), date, fixedRate);
    }
	
	@Override
    public void scheduleRunnableWithCronTrigger() {
        tpTaskscheduler.schedule(new RunnableTask("Cron Trigger"), cronTrigger);
    }
	
	@Override
    public void scheduleRunnableWithPeriodicTrigger() {
        tpTaskscheduler.schedule(new RunnableTask("Periodic Trigger"), periodicTrigger);
    }
	
	// Schedule Task to be executed every night at 00 or 12 am
	@Override
    public void addTaskToScheduler(int id, Runnable task) {
		ScheduledFuture<?> scheduledTask = taskScheduler.schedule(task, new CronTrigger("0 0 0 * * ?", TimeZone.getTimeZone(TimeZone.getDefault().getID())));
		jobsMap.put(id, scheduledTask);
	}
	
	// Remove scheduled task 
	@Override
    public void removeNamedTaskFromScheduler(String name) {
		ScheduledFuture<?> scheduledTask = namedJobsMap.get(name);
		if(scheduledTask != null) {
			scheduledTask.cancel(true);
			namedJobsMap.put(name, null);
		}
	}
	
	@Override
    public void removeTaskFromScheduler(int id) {
		ScheduledFuture<?> scheduledTask = jobsMap.get(id);
		if(scheduledTask != null) {
			scheduledTask.cancel(true);
			jobsMap.put(id, null);
		}
	}
	
	// A context refresh event listener
	@EventListener({ ContextRefreshedEvent.class })
	void contextRefreshedEvent() {
		// Get all tasks from DB and reschedule them in case of context restarted
	}

	@Override
	public List<String> getAllScheduledTasks() {
		return new ArrayList<>(this.namedJobsMap.keySet());
	}

	@Override
	public void scheduleRunnableWithTaskAndDate(String message, Long oldid, Long newid, Date date) {

		RunnableTaskTwo task = new RunnableTaskTwo(this.personRepository, message, oldid, newid);
		
		tpTaskscheduler.schedule(task, date);		
	}

	@Override
	public List<Person> findAll() {
		return this.personRepository.findAll();
	}
}
