package com.sample.microservices.rabbitmq.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfiguration {
	
    @Value("${spring.rabbitmq.queue-producer-service.queue-a-name}")
    private String queueA;

    @Value("${spring.rabbitmq.queue-producer-service.queue-b-name}")
    private String queueB;

    @Value("${spring.rabbitmq.queue-producer-service.queue-c-name}")
    private String queueC;

    @Value("${spring.rabbitmq.queue-producer-service.queue-d-name}")
    private String queueD;

    @Value("${spring.rabbitmq.queue-producer-service.queue-e-name}")
    private String queueE;

    @Value("${spring.rabbitmq.queue-producer-service.queue-f-name}")
    private String queueF;

    @Value("${spring.rabbitmq.queue-producer-service.queue-g-name}")
    private String queueG;

    @Bean
    public Queue queueA() {
        return new Queue(queueA);
    }

    @Bean
    public Queue queueB() {
        return new Queue(queueB);
    }

    @Bean
    public Queue queueC() {
        return new Queue(queueC);
    }

    @Bean
    public Queue queueD() {
        return new Queue(queueD);
    }

    @Bean
    public Queue queueE() {
        return new Queue(queueE);
    }

    @Bean
    public Queue queueF() {
        return new Queue(queueF);
    }
    
    @Bean
    public Queue queueG() {
        return new Queue(queueG);
    }
    
}
