package com.sample.microservices.rabbitmq.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfiguration {
	
// no need creating queues/exchange and binding them since it is only listening on existing queues.	
	
/*	
    @Value("${spring.rabbitmq.queue-consumer-service.direct-exchange-name}")
    private String directExchangeName;

    @Value("${spring.rabbitmq.queue-consumer-service.queue-a-name}")
    private String queueAName;

    @Value("${spring.rabbitmq.queue-consumer-service.queue-b-name}")
    private String queueBName;

    @Value("${spring.rabbitmq.queue-consumer-service.routing-a-name}")
    private String routingAName;

    @Value("${spring.rabbitmq.queue-consumer-service.routing-b-name}")
    private String routingBName;
    

    @Value("${spring.rabbitmq.queue-consumer-service.topic-exchange-name}")
    private String topicExchangeName;

    @Value("${spring.rabbitmq.queue-consumer-service.queue-c-name}")
    private String queueCName;

    @Value("${spring.rabbitmq.queue-consumer-service.queue-d-name}")
    private String queueDName;

    @Value("${spring.rabbitmq.queue-consumer-service.routing-c-name}")
    private String routingCName;

    @Value("${spring.rabbitmq.queue-consumer-service.routing-d-name}")
    private String routingDName;
    
    @Value("${spring.rabbitmq.queue-consumer-service.fanout-exchange-name}")
    private String fanoutExchangeName;

    @Value("${spring.rabbitmq.queue-consumer-service.queue-e-name}")
    private String queueEName;

    @Value("${spring.rabbitmq.queue-consumer-service.queue-f-name}")
    private String queueFName;

    @Bean
    public Exchange directExchange() {
        return ExchangeBuilder.directExchange(directExchangeName).durable(true).build();
    }

    @Bean
    public Queue queueA() {
        return new Queue(queueAName,true);
    }

    @Bean
    public Queue queueB() {
        return new Queue(queueBName,true);
    }

    @Bean
    public Binding bindingDirectExchangeQueueA() {
        return BindingBuilder.bind(queueA()).to(directExchange()).with(routingAName).noargs();
    }
    
    @Bean
    public Binding bindingDirectExchangeQueueB(DirectExchange directExchange, Queue queueB) {
        return BindingBuilder.bind(queueB).to(directExchange).with(routingBName);
    }
    
    //topic exchange
    @Bean
    public Exchange topicExchange() {
        return ExchangeBuilder.topicExchange(topicExchangeName).durable(true).build();
    }

    @Bean
    public Queue queueC() {
        return new Queue(queueCName,true);
    }

    @Bean
    public Queue queueD() {
        return new Queue(queueDName,true);
    }

    @Bean
    public Binding bindingTopicExchangeQueueA() {
        return BindingBuilder.bind(queueC()).to(topicExchange()).with(routingCName).noargs();
    }
    
    @Bean
    public Binding bindingTopicExchangeQueueB(DirectExchange topicExchange, Queue queueD) {
        return BindingBuilder.bind(queueD).to(topicExchange).with(routingDName);
    }
    
    //fanout exchange
    @Bean
    public Exchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange(fanoutExchangeName).durable(true).build();
    }

    @Bean
    public Queue queueE() {
        return new Queue(queueEName,true);
    }

    @Bean
    public Queue queueF() {
        return new Queue(queueFName,true);
    }

    @Bean
    public Binding bindingFanoutExchangeQueueA() {
        return BindingBuilder.bind(queueE()).to(fanoutExchange()).with("").noargs();
    }
    
    @Bean
    public Binding bindingFanoutExchangeQueueB(FanoutExchange fanoutExchange, Queue queueF) {
        return BindingBuilder.bind(queueF).to(fanoutExchange);
    }
*/    
}
