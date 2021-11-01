package com.sample.microservices.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.sample.microservices.kafka.model.User;

@Service
public class KafKaProducerServiceImpl implements KafKaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafKaProducerServiceImpl.class);
     
    @Value("${spring.kafka.topic01-name}")
    private String topic01Name;
 
    @Value("${spring.kafka.topic02-name}")
    private String topic02Name;
 
    @Value("${spring.kafka.topic03-name}")
    private String topic03Name;
 
    @Value("${spring.kafka.topic04-name}")
    private String topic04Name;
 
    public static final String TOPIC_NAME = "quickstart-events";

    private final KafkaTemplate<String, String> kafkaTemplate;
    
    private final KafkaTemplate<String, User> objectKafkaTemplate;
    
    KafKaProducerServiceImpl( final KafkaTemplate<String, String> kafkaTemplate,
    		final KafkaTemplate<String, User> objectKafkaTemplate) {
    	this.kafkaTemplate = kafkaTemplate;
    	this.objectKafkaTemplate = objectKafkaTemplate;
    }

    public String sendMessageToTopic01(String message) 
    {
        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(topic01Name, message);
        		
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

	        @Override
	        public void onSuccess(SendResult<String, String> result) {
	        	logger.info("Sent message=[" + message + 
        					"] with offset=[" + result.getRecordMetadata().offset() + 
        					"] with partition=[" + result.getRecordMetadata().partition() + 
	        				"] to Topic=[" + result.getProducerRecord().topic());
	        }
	        	
	        @Override
	        public void onFailure(Throwable ex) {
	            logger.info("Unable to send message=[" + message + 
	            		"] due to : " + ex.getMessage());
	        }
	        
        });
    	
        return topic01Name;
    }
    
    public String sendMessageToTopic02(String message) 
    {
        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(topic02Name, message);
        		
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

        	@Override
        	public void onSuccess(SendResult<String, String> result) {
	        	logger.info("Sent message=[" + message + 
    					"] with offset=[" + result.getRecordMetadata().offset() + 
    					"] with partition=[" + result.getRecordMetadata().partition() + 
        				"] to Topic=[" + result.getProducerRecord().topic());
        	}
        	
        	@Override
        	public void onFailure(Throwable ex) {
        	    logger.info("Unable to send message=[" + message + 
        	    		"] due to : " + ex.getMessage());
        	}
        });
        
        return topic02Name;
    	
    }
    
    public String sendMessageToTopic03(String message) 
    {
        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(topic03Name, message);
        		
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

        	@Override
        	public void onSuccess(SendResult<String, String> result) {
	        	logger.info("Sent message=[" + message + 
    					"] with offset=[" + result.getRecordMetadata().offset() + 
    					"] with partition=[" + result.getRecordMetadata().partition() + 
        				"] to Topic=[" + result.getProducerRecord().topic());
        	}
        	
        	@Override
        	public void onFailure(Throwable ex) {
        	    logger.info("Unable to send message=[" + message + 
        	    		"] due to : " + ex.getMessage());
        	}
        });
        
        return topic03Name;
    	
    }
    
    public String sendMessageToTopic04(String message) 
    {
        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(topic04Name, message);
        		
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

        	@Override
        	public void onSuccess(SendResult<String, String> result) {
	        	logger.info("Sent message=[" + message + 
    					"] with offset=[" + result.getRecordMetadata().offset() + 
    					"] with partition=[" + result.getRecordMetadata().partition() + 
        				"] to Topic=[" + result.getProducerRecord().topic());
        	}
        	
        	@Override
        	public void onFailure(Throwable ex) {
        	    logger.info("Unable to send message=[" + message + 
        	    		"] due to : " + ex.getMessage());
        	}
        });
        
        return topic04Name;
    	
    }
    
    public String sendMessageToTopic04WithPartitionId2(String message) 
    {
        Message<String> msg = MessageBuilder
        .withPayload(message)
        .setHeader(KafkaHeaders.TOPIC, topic04Name)
        .setHeader(KafkaHeaders.PARTITION_ID, 2)
        .build();
        
        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(msg);
        		
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

        	@Override
        	public void onSuccess(SendResult<String, String> result) {
	        	logger.info("Sent message=[" + message + 
    					"] with offset=[" + result.getRecordMetadata().offset() + 
    					"] with partition=[" + result.getRecordMetadata().partition() + 
        				"] to Topic=[" + result.getProducerRecord().topic());
        	}
        	
        	@Override
        	public void onFailure(Throwable ex) {
        	    logger.info("Unable to send message=[" + message + 
        	    		"] due to : " + ex.getMessage());
        	}
        });
        
        return topic04Name + "pid2";
    	
    }
    
    public String sendMessageToTopic03(User user) 
    {
        ListenableFuture<SendResult<String, User>> future = this.objectKafkaTemplate.send(topic03Name, user);
        		
        future.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {

        	@Override
        	public void onSuccess(SendResult<String, User> result) {
	        	logger.info("Sent message=[" + user + 
    					"] with offset=[" + result.getRecordMetadata().offset() + 
    					"] with partition=[" + result.getRecordMetadata().partition() + 
        				"] to Topic=[" + result.getProducerRecord().topic());
        	}
        	
        	@Override
        	public void onFailure(Throwable ex) {
        	    logger.info("Unable to send user=[" + user + 
        	    		"] due to : " + ex.getMessage());
        	}
        });

        return topic03Name;
    }
    
    public String sendMessageToTopic04(User user) 
    {
        ListenableFuture<SendResult<String, User>> future = this.objectKafkaTemplate.send(topic04Name, user);
        		
        future.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {

        	@Override
        	public void onSuccess(SendResult<String, User> result) {
	        	logger.info("Sent message=[" + user + 
    					"] with offset=[" + result.getRecordMetadata().offset() + 
    					"] with partition=[" + result.getRecordMetadata().partition() + 
        				"] to Topic=[" + result.getProducerRecord().topic());
        	}
        	
        	@Override
        	public void onFailure(Throwable ex) {
        	    logger.info("Unable to send user=[" + user + 
        	    		"] due to : " + ex.getMessage());
        	}
        });

        return topic04Name;
    }
    
}