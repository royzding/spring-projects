package com.sample.microservices.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.sample.microservices.batch.data.model.Account;
import com.sample.microservices.batch.data.model.Person;

public class MyItemProcessor implements ItemProcessor<Account, Person> {
    @Override
    public Person process(final Account account) throws Exception {
        Person person = new Person();
        person.setName(account.getName());      
        return person;
    }
}