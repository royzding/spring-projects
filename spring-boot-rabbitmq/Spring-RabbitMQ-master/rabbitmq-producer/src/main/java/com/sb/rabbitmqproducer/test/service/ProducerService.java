package com.sb.rabbitmqproducer.test.service;

import com.sb.rabbitmqproducer.test.model.Payload;

public interface ProducerService {
    void sendToDirectExchange(Payload payload, String routingKey);

    void sendToTopicExchange(Payload payload, String topic);

    void sendToFanoutExchange(Payload payload);
}
