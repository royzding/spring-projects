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
 
    @GetMapping("/topic01/{message}")
    public String sendMessageToTopic01(@PathVariable("message") String message) 
    {
        return this.producerService.sendMessageToTopic01(message);
    }
    
    @GetMapping("/topic02/{message}")
    public String sendMessageToTopic02(@PathVariable("message") String message) 
    {
        return this.producerService.sendMessageToTopic02(message);
    }
    
    @GetMapping("/topic03/{message}")
    public String sendMessageToTopic03(@PathVariable("message") String message) 
    {
        return this.producerService.sendMessageToTopic03(message);
    }
    
    @GetMapping("/topic04/{message}")
    public String sendMessageToTopic04(@PathVariable("message") String message) 
    {
        return this.producerService.sendMessageToTopic04(message);
    }
    
    @GetMapping("/topic04/pid2/{message}")
    public String sendMessageToTopic04WithPartitionId2(@PathVariable("message") String message) 
    {
        return this.producerService.sendMessageToTopic04WithPartitionId2(message);
    }
    
    
    
    @PostMapping(value = "/topic03/user")
    public String sendMessageToTopic03(@Valid @RequestBody() User user) 
    {
        return this.producerService.sendMessageToTopic03(user);
    }
    
    @PostMapping(value = "/topic04/user")
    public String sendMessageToTopic04(@Valid @RequestBody() User user) 
    {
        return this.producerService.sendMessageToTopic04(user);
    }
    
    
}