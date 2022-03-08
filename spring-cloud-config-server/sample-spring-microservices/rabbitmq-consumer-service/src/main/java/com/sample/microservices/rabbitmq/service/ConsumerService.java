package com.sample.microservices.rabbitmq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.microservices.rabbitmq.model.Payload;

@Service
public class ConsumerService {

/*	
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);
    
    private final RestTemplateService restTemplateService;
    
    public ConsumerService(RestTemplateService restTemplateService) {
    	this.restTemplateService = restTemplateService;
    }
*/ 
    @RabbitListener(queues = "${spring.rabbitmq.queue-consumer-service.queue-a-name}")
    public void receiveQueueAMessage(Payload payLoad) {
        System.out.println("ConsumerService:  Message received in Queue A : " + payLoad);
        
        //System.out.println("execute : " + this.restTemplateService.execute(payLoad));
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-consumer-service.queue-b-name}")
    public void receiveQueueBMessage(Payload payLoad) {
        System.out.println("ConsumerService:  Message received in Queue B : " + payLoad);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-consumer-service.queue-c-name}")
    public void receiveQueueCMessage(Payload payLoad) {
        System.out.println("ConsumerService:  Message received in Queue C : " + payLoad);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-consumer-service.queue-d-name}")
    public void receiveQueueDMessage(Payload payLoad) {
        System.out.println("ConsumerService:  Message received in Queue D : " + payLoad);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-consumer-service.queue-e-name}")
    public void receiveQueueEMessage(Payload payLoad) {
        System.out.println("ConsumerService:  Message received in Queue E : " + payLoad);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-consumer-service.queue-f-name}")
    public void receiveQueueFMessage(Payload payLoad) {
        System.out.println("ConsumerService:  Message received in Queue F : " + payLoad);
    }

}
