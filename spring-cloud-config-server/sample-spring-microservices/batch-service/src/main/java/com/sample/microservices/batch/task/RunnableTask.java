package com.sample.microservices.batch.task;

public class RunnableTask implements Runnable {

    private String message;

    public RunnableTask(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println("Runnable Task with " + message + " on thread " + getName());
    }
    
    public String getName() {
    	return Thread.currentThread().getName();
    }
}