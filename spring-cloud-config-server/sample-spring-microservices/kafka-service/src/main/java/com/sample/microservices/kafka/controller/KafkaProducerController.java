package com.sample.microservices.kafka.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.kafka.model.User;
import com.sample.microservices.kafka.model.User2;
import com.sample.microservices.kafka.service.KafKaProducerService;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducerController 
{
    private final KafKaProducerService producerService;
 
    public KafkaProducerController(final KafKaProducerService producerService) 
    {
        this.producerService = producerService;
    }
 
    //http://localhost:8088/service/kafka/Alphabet
    @GetMapping("{message}")
    public void sendMessageToTopic01(@PathVariable("message") String message) 
    {
    	System.out.println("sendMessageToTopic01: =======" + message);
        this.producerService.sendMessageToTopic01(message);
    }
    
    //http://localhost:8088/service/kafka/publish?message=Alphabet
    @PostMapping(value = "/publish")
    public void sendMessageToTopic02(@RequestParam("message") String message) 
    {
    	System.out.println("sendMessageToTopic02: =======" + message);
        this.producerService.sendMessageToTopic02(message);
    }
   
    //http://localhost:8088/service/kafka/publish?message=Alphabet
    @PostMapping(value = "/user")
    public void sendMessageToTopic02WithObject(@Valid @RequestBody() User2 user) 
    {
    	System.out.println("sendMessageToTopic03WithObject: =======" + user);
        this.producerService.sendMessageToTopic03WithObject(user);
    }
    
    
}