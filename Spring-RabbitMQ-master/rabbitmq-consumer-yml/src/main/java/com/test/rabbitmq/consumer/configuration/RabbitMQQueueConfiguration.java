package com.test.rabbitmq.consumer.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfiguration {
	
    @Value("${spring.rabbitmq.queue-listener-service.queue-name}")
    private String queueName;

    @Value("${spring.rabbitmq.queue-listener-service.exchange-name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.queue-listener-service.routing-name}")
    private String routingName;

    @Value("${spring.rabbitmq.queue-listener-service.routingx-name}")
    private String routingxName;

    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeName);
    }
/*    
    @Bean
    public Binding bindingDirectExchangeQueueA(DirectExchange directExchange, Queue queueA) {
        return BindingBuilder.bind(queueA).to(directExchange).with(routingName);
    }
*/    
    @Bean
    public Binding bindingDirectExchangeQueueB(DirectExchange directExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with(routingxName);
    }
    
}
