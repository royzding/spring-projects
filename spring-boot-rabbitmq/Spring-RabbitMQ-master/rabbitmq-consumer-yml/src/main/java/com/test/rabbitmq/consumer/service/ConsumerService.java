package com.test.rabbitmq.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.rabbitmq.consumer.model.Payload;

@Service
public class ConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);
    
    @Autowired
    RestTemplateService restTemplateService;

    @RabbitListener(queues = "${spring.rabbitmq.queue-consumer-service.queue-a-name}")
    public void receiveQueueAMessage(Payload payLoad) {
        LOGGER.info("Message received in Queue A : " + payLoad);
        
        LOGGER.info("execute : " + this.restTemplateService.execute(payLoad));
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-consumer-service.queue-b-name}")
    public void receiveQueueBMessage(Payload payLoad) {
        LOGGER.info("Message received in Queue B : " + payLoad);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-consumer-service.queue-c-name}")
    public void receiveQueueCMessage(Payload payLoad) {
        LOGGER.info("Message received in Queue C : " + payLoad);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-consumer-service.queue-d-name}")
    public void receiveQueueDMessage(Payload payLoad) {
        LOGGER.info("Message received in Queue D : " + payLoad);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-consumer-service.queue-e-name}")
    public void receiveQueueEMessage(Payload payLoad) {
        LOGGER.info("Message received in Queue E : " + payLoad);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-consumer-service.queue-f-name}")
    public void receiveQueueFMessage(Payload payLoad) {
        LOGGER.info("Message received in Queue F : " + payLoad);
    }

}
