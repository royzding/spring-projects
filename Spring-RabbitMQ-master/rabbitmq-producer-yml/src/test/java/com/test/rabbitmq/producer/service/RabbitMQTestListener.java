package com.test.rabbitmq.producer.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.test.rabbitmq.producer.model.Payload;

@Component
public class RabbitMQTestListener {
    public Payload payload;

    @RabbitListener(queues = "testQueue")
    public void listen(Payload payload) {
        this.payload = payload;
    }
}
