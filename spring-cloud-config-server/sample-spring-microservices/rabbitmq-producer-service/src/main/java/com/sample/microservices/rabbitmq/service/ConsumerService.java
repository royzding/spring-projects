package com.sample.microservices.rabbitmq.service;

import com.sample.microservices.rabbitmq.model.Payload;

public interface ConsumerService {
	
    void receiveQueueAMessage1(Payload payLoad);
    void receiveQueueAMessage2(Payload payLoad);
    void receiveQueueBMessage(Payload payLoad);
    void receiveQueueCMessage(Payload payLoad);
    void receiveQueueDMessage(Payload payLoad);
    void receiveQueueEMessage(Payload payLoad);
    void receiveQueueFMessage(Payload payLoad);
    
}
