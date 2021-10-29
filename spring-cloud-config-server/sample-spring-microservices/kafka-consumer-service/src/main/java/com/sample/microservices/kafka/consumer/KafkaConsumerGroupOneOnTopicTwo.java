package com.sample.microservices.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.sample.microservices.kafka.model.User;
 
@Service
public class KafkaConsumerGroupOneOnTopicTwo
{
    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerGroupOneOnTopicTwo.class);
/*    
    @KafkaListener(topics = "${spring.kafka.topic02-name}", groupId = "${spring.kafka.consumer.group01-id}")
    public void consume1(String message) 
    {
    	logger.info("consume1: Message recieved:" + message);
    }

    @KafkaListener(topics = "${spring.kafka.topic02-name}", groupId = "${spring.kafka.consumer.group01-id}")
    public void consume2(String message) 
    {
    	logger.info("consume2: Message recieved:" + message);
    }

    @KafkaListener(topics = "${spring.kafka.topic02-name}")
    public void listenWithHeaders(@Payload String message, 
    		@Header(KafkaHeaders.OFFSET) Integer offset,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) 
    {    	
    	logger.info("Processing topic = {}, partition = {}, offset = {}, message = {}",
                topic, partition, offset, message);         
    }
*/       
}