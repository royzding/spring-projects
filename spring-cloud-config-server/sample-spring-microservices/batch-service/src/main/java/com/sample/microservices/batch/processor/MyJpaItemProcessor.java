package com.sample.microservices.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.sample.microservices.batch.data.model.ManagerEntity;
import com.sample.microservices.batch.data.model.Person;

public class MyJpaItemProcessor implements ItemProcessor<ManagerEntity, Person> {
    @Override
    public Person process(final ManagerEntity manager) throws Exception {
    	Person person = new Person();
    	person.setName(manager.getName());      
        return person;
    }
}