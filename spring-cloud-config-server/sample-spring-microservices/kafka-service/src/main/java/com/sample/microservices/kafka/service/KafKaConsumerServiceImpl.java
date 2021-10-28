package com.sample.microservices.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sample.microservices.kafka.model.User;
 
@Service
public class KafKaConsumerServiceImpl implements KafKaConsumerService
{
    private final Logger logger = LoggerFactory.getLogger(KafKaConsumerServiceImpl.class);
    
	@Override
    @KafkaListener(topics = "${spring.kafka.topic01-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume01OneTopic(String message) 
    {
        System.out.println("###################consume01OneTopic:" + "Message recieved:" + message);
    }
	
	@Override
    @KafkaListener(topics = "${spring.kafka.topic01-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume02OneTopic(String message) 
    {
        System.out.println("###################consume02OneTopic:" + "Message recieved:" + message);
    }
	
	@Override
	@KafkaListener(
			  topics = "${spring.kafka.topic01-name}", groupId = "${spring.kafka.consumer.group-id}",  containerFactory = "filterKafkaListenerContainerFactory"
	)
	public void listenWithFilter(String message) {
		System.out.println("################### listenWithFilter: Received Message in filtered listener: " + message);
	}


	@Override
    @KafkaListener(topics = "${spring.kafka.topic02-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMultipleTopics(String message) 
    {
        System.out.println("###################consumeMultipleTopics:" + "Message recieved:" + message);
    }

	@Override
    @KafkaListener(topics = "${spring.kafka.topic03-name}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "objectKafkaListenerContainerFactory")
    public void consumeWithObject(User user) 
    {
	        System.out.println("###################consumeWithObject:" + "User recieved:" + user);			
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