package com.sample.microservices.batch.task;

import java.util.Date;
import java.util.Optional;

import com.sample.microservices.batch.data.model.Person;
import com.sample.microservices.batch.repository.PersonRepository;

public class RunnableTaskTwo implements Runnable {

    private String message;
    private Long oldId;
    private Long newId;

    private final PersonRepository personRepository;

    public RunnableTaskTwo(PersonRepository personRepository, String message, Long oldId, Long newId) {
        this.message = message;
        this.oldId = oldId;
        this.newId = newId;
        this.personRepository = personRepository;
    }

    @Override
    public void run() {
        System.out.println("Runnable TaskTwo with " + message 
        		+ " flip " + oldId + " to " + newId 
        		+ " on thread " + getName()
        		+ " at time of " + new Date());
        
        Optional<Person> oldPerson = this.personRepository.findById(oldId);
        
        Optional<Person> newPerson = this.personRepository.findById(newId);
        
        Person op = oldPerson.get();
        Person np = newPerson.get();
        
        op.setName("name-inactive");
        np.setName("name-active");
        
        this.personRepository.save(op);
        this.personRepository.save(np);
               
    }
    
    public String getName() {
    	return Thread.currentThread().getName();
    }
}