package com.test.rabbitmq.consumer.configuration;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConsumerConfiguration implements RabbitListenerConfigurer {
	
    @Value("${threadpool.corepoolsize}")
    int corePoolSize;
     
    @Value("${threadpool.maxpoolsize}")
    int maxPoolSize;
   
    @Value("${spring.rabbitmq.host}")
    String host;
   
    @Value("${spring.rabbitmq.port}")
    int port;
   
    @Value("${spring.rabbitmq.username}")
    String username;
   
    @Value("${spring.rabbitmq.password}")
    String password;
   
    @Bean
    public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
    	return mapper;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory defaultMessageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        defaultMessageHandlerMethodFactory.setMessageConverter(messageConverter());
        return defaultMessageHandlerMethodFactory;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
    	return builder.build();
    }
     
    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(corePoolSize);
        pool.setMaxPoolSize(maxPoolSize);
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

   @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setPrefetchCount(50);
        factory.setConcurrentConsumers(5);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }
    
	@Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
}
