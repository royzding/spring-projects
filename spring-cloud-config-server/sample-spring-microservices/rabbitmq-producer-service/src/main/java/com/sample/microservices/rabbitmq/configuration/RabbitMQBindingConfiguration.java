package com.sample.microservices.rabbitmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQBindingConfiguration {

    @Value("${spring.rabbitmq.queue-producer-service.routing-a-name}")
    private String direct1RoutingKey;

    @Value("${spring.rabbitmq.queue-producer-service.routing-b-name}")
    private String direct2RoutingKey;

    @Value("${spring.rabbitmq.queue-producer-service.routing-c-name}")
    private String topicRabbitMQRoutingKey;

    @Value("${spring.rabbitmq.queue-producer-service.routing-d-name}")
    private String topicRabbitMQSpringRoutingKey;

    @Bean
    public Binding bindingDirectExchangeQueueADirect1(DirectExchange directExchange, Queue queueA) {
        return BindingBuilder.bind(queueA).to(directExchange).with(direct1RoutingKey);
    }

    @Bean
    public Binding bindingDirectExchangeQueueBDirect2(DirectExchange directExchange, Queue queueB) {
        return BindingBuilder.bind(queueB).to(directExchange).with(direct2RoutingKey);
    }

    @Bean
    public Binding bindingTopicExchangeQueueCTopicRabbitMQ(TopicExchange topicExchange, Queue queueC) {
        return BindingBuilder.bind(queueC).to(topicExchange).with(topicRabbitMQRoutingKey);
    }

    @Bean
    public Binding bindingTopicExchangeQueueDTopicRabbitMQSpring(TopicExchange topicExchange, Queue queueD) {
        return BindingBuilder.bind(queueD).to(topicExchange).with(topicRabbitMQSpringRoutingKey);
    }

    @Bean
    public Binding bindingFanoutExchangeQueueEFanout(FanoutExchange fanoutExchange, Queue queueE) {
        return BindingBuilder.bind(queueE).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanoutExchangeQueueFFanout(FanoutExchange fanoutExchange, Queue queueF) {
        return BindingBuilder.bind(queueF).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanoutExchangeQueueGFanout(FanoutExchange fanoutExchange, Queue queueG) {
        return BindingBuilder.bind(queueG).to(fanoutExchange);
    }

}
