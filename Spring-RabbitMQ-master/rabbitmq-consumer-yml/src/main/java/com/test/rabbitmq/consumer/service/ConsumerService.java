package com.test.rabbitmq.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.test.rabbitmq.consumer.model.Payload;

@Service
public class ConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

    @RabbitListener(queues = "${spring.rabbitmq.queue-listener-service.queue-a-name}")
    public void receiveQueueAMessage(Payload payLoad) {
        LOGGER.info("Message received in Queue A : " + payLoad);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-listener-service.queue-b-name}")
    public void receiveQueueBMessage(Payload payLoad) {
        LOGGER.info("Message received in Queue B : " + payLoad);
    }

}
