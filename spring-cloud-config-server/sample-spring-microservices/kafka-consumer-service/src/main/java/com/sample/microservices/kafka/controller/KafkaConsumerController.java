package com.sample.microservices.kafka.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.kafka.data.model.KafkaConsumerMessageEntity;
import com.sample.microservices.kafka.service.KafKaConsumerService;

@RestController
@RequestMapping(value = "/consumer")
public class KafkaConsumerController 
{
    private final KafKaConsumerService kkcService;
 
    public KafkaConsumerController(final KafKaConsumerService kkcService) 
    {
        this.kkcService = kkcService;
    }
 
    @GetMapping
    public List<KafkaConsumerMessageEntity> getAllConsumerMessages() 
    {
    	return this.kkcService.getAllConsumerMessages();
    }
    
    @DeleteMapping
    public void deleteAll() 
    {
    	this.kkcService.deleteAll();
    }
   
    
}