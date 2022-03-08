package com.sample.microservices.rabbitmq.service;

import com.sample.microservices.rabbitmq.model.Payload;

public interface ConsumerService {
	
    void handleMessage(Payload payload);
    
}
