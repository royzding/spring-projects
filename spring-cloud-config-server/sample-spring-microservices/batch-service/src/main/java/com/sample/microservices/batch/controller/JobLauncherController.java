package com.sample.microservices.batch.controller;
 
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.batch.data.model.Person;
import com.sample.microservices.batch.repository.PersonRepository;

@RestController
@RequestMapping("/job-launcher")
public class JobLauncherController {
 
    @Autowired
    JobLauncher jobLauncher;
     
    @Autowired
    Job job;
    
	@Autowired
	@Lazy
	private PersonRepository pRepo;

	@PostMapping("/invokejob")
    public void handle() {
    	
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
    
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException
                | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {

            e.printStackTrace();
        }
        
    }
    
    @DeleteMapping("/person/all")
    public void deleteAllPersons() {
    	
    	this.pRepo.deleteAll();
    }
    
    @GetMapping("/person/all")
    public List<Person> getAllPersons() {
    	
    	return this.pRepo.findAll();
    }
}