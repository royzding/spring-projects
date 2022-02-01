package com.sample.microservices.rabbitmq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.rabbitmq.model.Context;
import com.sample.microservices.rabbitmq.model.Payload;

@RestController
@RequestMapping(value = "/payload")
public class ConsumerController {
	
    @GetMapping
    public Payload getPayLoad() {
    	
    	Payload payload = new Payload();
    	payload.setMessage1("msg1");
    	payload.setMessage2("msg2");
    	payload.setTaskId(12);
    	
    	Context context = new Context();
    	context.setC1("c1");
    	context.setC2("c2");
    	
    	payload.setContext(context);
    	
    	
    	
    	return payload;
    }

}