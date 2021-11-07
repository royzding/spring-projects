package com.sample.microservices.batch.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.sample.microservices.batch.repository.PersonRepository;
 
public class MyTaskOne implements Tasklet {
	
	private PersonRepository pRepo;
	
	public MyTaskOne(PersonRepository pRepo) {
		this.pRepo = pRepo;		
	}
 
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception 
    {
        System.out.println("MyTaskOne start..");
 
        this.pRepo.findAll().forEach(e->System.out.println("MyTaskOne: " + e.getName()));
         
        System.out.println("MyTaskOne done..");
        return RepeatStatus.FINISHED;
    }    
}