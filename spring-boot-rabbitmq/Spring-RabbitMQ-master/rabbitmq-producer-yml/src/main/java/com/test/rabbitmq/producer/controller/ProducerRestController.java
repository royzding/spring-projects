package com.test.rabbitmq.producer.controller;

import com.test.rabbitmq.producer.model.Payload;
import com.test.rabbitmq.producer.service.ProducerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/producer")
public class ProducerRestController {
    @Autowired
    private ProducerService producerService;
    
    @GetMapping
    public String welcome() {
    	return "Welcome from producer";
    }

    @PostMapping(
            value = "/direct/{routingKey}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity sendToDirectExchange(@PathVariable(value = "routingKey") String routingKey,
                                               @RequestBody Payload payload) {
        producerService.sendToDirectExchange(payload, routingKey);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(
            value = "/topic/{topic}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity sendToTopicExchange(@PathVariable(value = "routingKey") String routingKey,
                                              @RequestBody Payload payload) {
        producerService.sendToTopicExchange(payload, routingKey);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(
            value = "/fanout",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity sendToFanoutExchange(@RequestBody Payload payload) {
        producerService.sendToFanoutExchange(payload);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
