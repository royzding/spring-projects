package com.sample.microservices.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sample.microservices.rabbitmq.model.Payload;
import com.sample.microservices.rabbitmq.service.ProducerService;

@RestController
@RequestMapping(value = "/api/producer")
public class ProducerRestController {

    private final ProducerService producerService;    
    public ProducerRestController(ProducerService producerService) {
    	this.producerService = producerService;
    }
    
    @GetMapping
    public String welcome() {
    	return "Welcome from producer";
    }

    @PutMapping(
            value = "/direct/{routingKey}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity sendToDirectExchangeUsingPut(@PathVariable(value = "routingKey") String routingKey,
                                               @RequestBody Payload payload) {
        producerService.sendToDirectExchange(payload, routingKey);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(
            value = "/direct/{routingKey}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity sendToDirectExchangeUsingDelete(@PathVariable(value = "routingKey") String routingKey,
                                               @RequestBody Payload payload) {
        producerService.sendToDirectExchange(payload, routingKey);
        return ResponseEntity.status(HttpStatus.OK).build();
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
            value = "/topic/{routingKey}",
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
