package com.test.rabbitmq.producer.service;

import com.test.rabbitmq.producer.model.Payload;

public interface ProducerService {
	
    void sendToDirectExchange(Payload payload, String routingKey);

    void sendToTopicExchange(Payload payload, String routingKey);

    void sendToFanoutExchange(Payload payload);
}
