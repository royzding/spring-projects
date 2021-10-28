package com.sample.microservices.asyncaop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
 
@Service
public class KafKaConsumerServiceImpl implements KafKaConsumerService
{
    private final Logger logger = LoggerFactory.getLogger(KafKaConsumerService.class);
    
    @Value("${spring.kafka.topic-name}")
    private String topic_name;
 
    @Value("${spring.kafka.consumer.group-id}")
    private String group_id;
    
    public static final String TOPIC_NAME = "quickstart-events";
    public static final String GROUP_ID = "group-id";
 
    @KafkaListener(topics = TOPIC_NAME, groupId = GROUP_ID)
    public void consume(String message) 
    {
        logger.info(String.format("Message recieved -> %s", message));
        
        System.out.println("$$$$$$$$$$$$$$$$$$$" + "Message recieved:" + message);
    }
}