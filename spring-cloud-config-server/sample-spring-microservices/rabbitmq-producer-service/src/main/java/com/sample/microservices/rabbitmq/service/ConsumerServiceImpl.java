package com.sample.microservices.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.sample.microservices.rabbitmq.model.Payload;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	@Override
	@RabbitListener(queues = "${spring.rabbitmq.queue-producer-service.queue-a-name}")
	public void handleMessage(Payload payload) {
		
		System.out.println(payload);
		
		
	}
	
    
}
