package com.sample.microservices.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.sample.microservices.rabbitmq.model.Payload;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	@Override
    @RabbitListener(queues = "${spring.rabbitmq.queue-producer-service.queue-a-name}")
    public void receiveQueueAMessage1(Payload payLoad) {
        System.out.println("ProducerService:  Message received in Queue A1 : " + payLoad);
    }

	@Override
    @RabbitListener(queues = "${spring.rabbitmq.queue-producer-service.queue-a-name}")
    public void receiveQueueAMessage2(Payload payLoad) {
        System.out.println("ProducerService:  Message received in Queue A2 : " + payLoad);
    }

	@Override
    @RabbitListener(queues = "${spring.rabbitmq.queue-producer-service.queue-b-name}")
    public void receiveQueueBMessage(Payload payLoad) {
        System.out.println("ProducerService:  Message received in Queue B : " + payLoad);
    }
	
	@Override	 
    @RabbitListener(queues = "${spring.rabbitmq.queue-producer-service.queue-c-name}")
    public void receiveQueueCMessage(Payload payLoad) {
        System.out.println("ProducerService:  Message received in Queue C : " + payLoad);
    }

	@Override	 
    @RabbitListener(queues = "${spring.rabbitmq.queue-producer-service.queue-d-name}")
    public void receiveQueueDMessage(Payload payLoad) {
        System.out.println("ProducerService:  Message received in Queue D : " + payLoad);
    }

	@Override	 
    @RabbitListener(queues = "${spring.rabbitmq.queue-producer-service.queue-e-name}")
    public void receiveQueueEMessage(Payload payLoad) {
        System.out.println("ProducerService:  Message received in Queue E : " + payLoad);
    }

	@Override	 
    @RabbitListener(queues = "${spring.rabbitmq.queue-producer-service.queue-f-name}")
    public void receiveQueueFMessage(Payload payLoad) {
        System.out.println("ProducerService:  Message received in Queue F : " + payLoad);
    }
    
}
