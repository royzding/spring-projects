package com.sample.microservices.rabbitmq.service;

import com.sample.microservices.rabbitmq.model.Payload;

public interface ProducerService {
	
    void sendToDirectExchange(Payload payload, String routingKey);

    void sendToTopicExchange(Payload payload, String routingKey);

    void sendToFanoutExchange(Payload payload);
}
