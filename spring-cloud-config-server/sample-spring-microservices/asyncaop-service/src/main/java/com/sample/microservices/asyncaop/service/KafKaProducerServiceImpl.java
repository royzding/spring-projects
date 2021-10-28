package com.sample.microservices.asyncaop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafKaProducerServiceImpl implements KafKaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafKaProducerService.class);
     
    @Value("${spring.kafka.topic-name}")
    private String topicName;
 
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    public static final String TOPIC_NAME = "quickstart-events";

    private final KafkaTemplate<String, String> kafkaTemplate;
    
    KafKaProducerServiceImpl(final KafkaTemplate<String, String> kafkaTemplate) {
    	this.kafkaTemplate = kafkaTemplate;
    }
 
    public void sendMessage(String message) 
    {
        logger.info(String.format("Message sent -> %s", message));
        this.kafkaTemplate.send(topicName, message);
        System.out.println("----------------->>>>>" + "Message set:" + message);
    }
}