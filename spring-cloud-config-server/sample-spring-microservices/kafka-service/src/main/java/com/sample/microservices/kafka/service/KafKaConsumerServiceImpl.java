package com.sample.microservices.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
 
@Service
public class KafKaConsumerServiceImpl implements KafKaConsumerService
{
    private final Logger logger = LoggerFactory.getLogger(KafKaConsumerServiceImpl.class);
    
    @Value("${spring.kafka.topic01-name}")
    private String topic01Name;
 
    @Value("${spring.kafka.consumer.group-id}")
    private String group_id;
    
    public static final String TOPIC_NAME = "topic-01";
    public static final String TOPIC_NAMES = "topic-01,topic-02";
    
    public static final String GROUP_ID = "group-id";
 
	@Override
    @KafkaListener(topics = TOPIC_NAME, groupId = GROUP_ID)
    public void consumeOneTopic(String message) 
    {
        logger.info(String.format("consumeOneTopic: Message recieved -> %s", message));
        
        System.out.println("$$$$$$$$$$$$$$$$$$$" + "Message recieved:" + message);
    }

	@Override
    @KafkaListener(topics = "topic-02", groupId = GROUP_ID)
    public void consumeMultipleTopics(String message) 
    {
        logger.info(String.format("consumeMultipleTopics: Message recieved -> %s", message));
        
        System.out.println("###################" + "Message recieved:" + message);
    }

	@Override
	public void consume(String message) {
		// TODO Auto-generated method stub
		
	}

/*    
    @KafkaListener(topics = TOPIC_NAME)
    public void listenWithHeaders(
      @Payload String message, 
      @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {    	
          System.out.println("Received Message: " + message  + "from partition: " + partition);
    }
*/        
}