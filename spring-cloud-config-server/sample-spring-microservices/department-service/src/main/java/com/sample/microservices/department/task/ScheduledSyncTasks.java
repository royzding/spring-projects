package com.sample.microservices.department.task;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sample.microservices.department.repository.DepartmentRepository;

@Component
public class ScheduledSyncTasks {
	
	private final DepartmentRepository repository;
		
	public ScheduledSyncTasks(DepartmentRepository repository) {
		this.repository = repository;
	}
	
/*
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        System.out.println("Fixed delay task - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedDelayString = "${schedule.fixedDelay.in.milliseconds}")
    public void scheduleFixedDelayTaskUsingExpression() {
        System.out.println("Fixed delay task - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 2000)
    public void scheduleFixedDelayWithInitialDelayTask() {
        System.out.println("Fixed delay task with one second initial delay - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() {
        System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedRateString = "${schedule.fixedRate.in.milliseconds}")
    public void scheduleFixedRateTaskUsingExpression() {
        System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void scheduleFixedRateWithInitialDelayTask() {
        long now = System.currentTimeMillis() / 1000;
        System.out.println("Fixed rate task with one second initial delay - " + now);
    }
*/
	
    @Scheduled(cron = "${schedule.cron.expression}")
    public void scheduleTaskUsingExternalizedCronExpression() {
        System.out.println("schedule tasks using externalized cron expressions - running at " + LocalDateTime.now());        
        this.repository.insertTwoParam(1, "scheduleTaskUsingExternalizedCronExpression");
    }

    /**
     * Scheduled task is executed at 10:15 AM on the 15th day of every month
     * seconds minutes hours day-of-month month day-of-week
     * 0       0       8        *         *        ?
     * 0 0 8 * * ? means that the task is executed at 08:00:00 every day.
     * 
     * 0 0 2 12 3 ? means that the task is executed at Sun, Mar 12, 2023 2:00 AM.
     * 0 0 2 5 11 ? means that the task is executed at Sun, Nov 5, 2023 2:00 AM.
     * 
     * @Scheduled(cron = "0 30 15 1 2 ?", zone = "UTC")
     * 
     */
    @Scheduled(cron = "0 15 19 2 2 ?")
    public void scheduleTaskUsingCronExpression() {
        System.out.println("schedule tasks using cron jobs - running at " + LocalDateTime.now());        
        this.repository.insertTwoParam(-1, "scheduleTaskUsingCronExpression");

    }

    @Scheduled(cron = "0 52 9 3 2 ?")
    public void pInsertTwoParamPlusOne() {
        System.out.println("schedule tasks pInsertTwoParamPlusOne - running at " + LocalDateTime.now());       
        this.repository.pInsertTwoParamPlusOne();

    }

    @Scheduled(cron = "0 55 9 3 2 ?")
    public void pInsertTwoParamMinusOne() {
        System.out.println("schedule tasks pInsertTwoParamMinusOne - running at " + LocalDateTime.now());       
        this.repository.pInsertTwoParamMinusOne();

    }


}