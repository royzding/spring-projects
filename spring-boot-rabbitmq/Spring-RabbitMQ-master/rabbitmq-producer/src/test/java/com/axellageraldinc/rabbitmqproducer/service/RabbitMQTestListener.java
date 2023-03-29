package com.axellageraldinc.rabbitmqproducer.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.sb.rabbitmqproducer.test.model.Payload;

@Component
public class RabbitMQTestListener {
    public Payload payload;

    @RabbitListener(queues = "testQueue")
    public void listen(Payload payload) {
        this.payload = payload;
    }
}
