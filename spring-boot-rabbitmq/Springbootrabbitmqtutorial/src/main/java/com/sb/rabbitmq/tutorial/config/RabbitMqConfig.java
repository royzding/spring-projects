package com.sb.rabbitmq.tutorial.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sb.rabbitmq.tutorial.listen.Subscriber3;

// Marker annotation that tells spring to generate bean definitions at runtime for the methods annotated with @Bean annotation.
@Configuration
public class RabbitMqConfig {

	// Value is populated with the queue name from "application.properties" file.
	@Value("${rabbitmq.queue}")
	private String queueName;
	
	// Value is populated with the exchange name from "application.properties" file.
	@Value("${rabbitmq.exchange}")
	private String exchange;
	
	// Value is populated with the routing key from "application.properties" file.
	@Value("${rabbitmq.routingkey}")
	private String routingKey;

	// @Bean annotation tells that a method produces a bean which is to be managed by the spring container.
	@Bean
	Queue queue() {
		// Creating a queue.
		return new Queue(queueName, Boolean.FALSE);
	}

	@Bean
	TopicExchange topicExchange() {
		// Creating a topic exchange.
		return new TopicExchange(exchange);
	}

	@Bean
	Binding binding(final Queue queue, final TopicExchange topicExchange) {
		// Binding the queue to the topic with a routing key.
		return BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
	}

	@Bean
	MessageConverter jacksonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jacksonMessageConverter());
		return rabbitTemplate;
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
	      MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	    container.setConnectionFactory(connectionFactory);
	    container.setQueueNames(queueName);
	    container.setMessageListener(listenerAdapter);
	    return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Subscriber3 receiver) {
		MessageListenerAdapter messageListenerAdapter =new MessageListenerAdapter(receiver, "receiveMessage");
		messageListenerAdapter.setMessageConverter(jacksonMessageConverter());
		
		return messageListenerAdapter;
	}	
}
