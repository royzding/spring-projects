package com.sample.microservices.batch.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.sample.microservices.batch.util.PrintIt.println;

@Component
public class ScheduledSyncTasks {

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        println("Fixed delay task - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedDelayString = "${schedule.fixedDelay.in.milliseconds}")
    public void scheduleFixedDelayTaskUsingExpression() {
        println("Fixed delay task - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 2000)
    public void scheduleFixedDelayWithInitialDelayTask() {
        println("Fixed delay task with one second initial delay - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() {
        println("Fixed rate task - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedRateString = "${schedule.fixedRate.in.milliseconds}")
    public void scheduleFixedRateTaskUsingExpression() {
        println("Fixed rate task - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void scheduleFixedRateWithInitialDelayTask() {
        long now = System.currentTimeMillis() / 1000;
        println("Fixed rate task with one second initial delay - " + now);
    }

    /**
     * Scheduled task is executed at 10:15 AM on the 15th day of every month
     */
    @Scheduled(cron = "0 15 10 15 * ?")
    public void scheduleTaskUsingCronExpression() {
        long now = System.currentTimeMillis() / 1000;
        println("schedule tasks using cron jobs - " + now);
    }

    @Scheduled(cron = "${schedule.cron.expression}")
    public void scheduleTaskUsingExternalizedCronExpression() {
        println("schedule tasks using externalized cron expressions - " + System.currentTimeMillis() / 1000);
    }

}