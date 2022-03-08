package com.sample.microservices.rabbitmq.configuration;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQExchangeConfiguration {

    @Value("${spring.rabbitmq.queue-producer-service.direct-exchange-name}")
    private String directExchange;

    @Value("${spring.rabbitmq.queue-producer-service.topic-exchange-name}")
    private String topicExchange;

    @Value("${spring.rabbitmq.queue-producer-service.fanout-exchange-name}")
    private String fanoutExchange;

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(directExchange);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchange);
    }
  
}
