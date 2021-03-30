package com.test.rabbitmq.consumer.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfiguration {
	
    @Value("${spring.rabbitmq.queue-listener-service.queue-a-name}")
    private String queueAName;

    @Value("${spring.rabbitmq.queue-listener-service.queue-b-name}")
    private String queueBName;

    @Value("${spring.rabbitmq.queue-listener-service.exchange-name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.queue-listener-service.routing-name}")
    private String routingName;

    @Value("${spring.rabbitmq.queue-listener-service.routingx-name}")
    private String routingxName;

    @Bean
    public Queue queueA() {
        return new Queue(queueAName,true);
    }

    @Bean
    public Queue queueB() {
        return new Queue(queueBName,true);
    }

    @Bean
    public Exchange directExchange() {
        return ExchangeBuilder.directExchange(exchangeName).durable(true).build();
    }
/*    
    @Bean
    public Binding bindingDirectExchangeQueueA(DirectExchange directExchange, Queue queueA) {
        return BindingBuilder.bind(queueA).to(directExchange).with(routingName);
    }
*/    
    @Bean
    public Binding bindingDirectExchangeQueueA() {
        return BindingBuilder.bind(queueA()).to(directExchange()).with(routingxName).noargs();
    }
    
    @Bean
    public Binding bindingDirectExchangeQueueB(DirectExchange directExchange, Queue queueB) {
        return BindingBuilder.bind(queueB).to(directExchange).with(routingxName);
    }
    
}
