package com.sample.microservices.rabbitmq.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sample.microservices.rabbitmq.model.Payload;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Value("${spring.rabbitmq.queue-producer-service.direct-exchange-name}")
    private String directExchange;

    @Value("${spring.rabbitmq.queue-producer-service.topic-exchange-name}")
    private String topicExchange;

    @Value("${spring.rabbitmq.queue-producer-service.fanout-exchange-name}")
    private String fanoutExchange;

    @Autowired
    //private RabbitTemplate rabbitTemplate;
    private AmqpTemplate rabbitTemplate;

    @Override
    public void sendToDirectExchange(Payload payload, String routingKey) {
        rabbitTemplate.convertAndSend(directExchange, routingKey, payload);
    }

    @Override
    public void sendToTopicExchange(Payload payload, String topic) {
        rabbitTemplate.convertAndSend(topicExchange, topic, payload);
    }

    @Override
    public void sendToFanoutExchange(Payload payload) {
        rabbitTemplate.convertAndSend(fanoutExchange, "", payload); // routingKey is ignored
    }
}
