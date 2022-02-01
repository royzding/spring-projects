package com.sample.microservices.rabbitmq.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.test.TestRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sample.microservices.rabbitmq.configuration.RabbitMQProducerTestConfiguration;
import com.sample.microservices.rabbitmq.model.Payload;

@SpringBootTest(classes = {RabbitMQProducerTestConfiguration.class, RabbitMQTestListener.class})
public class ProducerServiceImplIntegrationTest {
    @Autowired
    private TestRabbitTemplate testRabbitTemplate;
    @Autowired
    private RabbitMQTestListener rabbitMQTestListener;

    @Test
    public void sendToDirectExchange_Success() {
    	
        testRabbitTemplate.convertAndSend("directExchange", "testQueue", new Payload());
        assertThat(rabbitMQTestListener.payload.getMessage1(), equalTo("test"));
    }
}