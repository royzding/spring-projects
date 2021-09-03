package com.sample.microservices.asyncaop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.asyncaop.service.KafKaProducerService;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducerController 
{
    private final KafKaProducerService producerService;
 
    public KafkaProducerController(final KafKaProducerService producerService) 
    {
        this.producerService = producerService;
    }
 
    //http://localhost:8088/service/kafka/publish?message=Alphabet
    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) 
    {
    	System.out.println("=======" + message);
        this.producerService.sendMessage(message);
    }
    
    //http://localhost:8088/service/kafka/Alphabet
    @GetMapping("{message}")
    public void sendMessageToKafkaTopicUsingGet(@PathVariable("message") String message) 
    {
    	System.out.println("=======" + message);
        this.producerService.sendMessage(message);
    }
    
    
}