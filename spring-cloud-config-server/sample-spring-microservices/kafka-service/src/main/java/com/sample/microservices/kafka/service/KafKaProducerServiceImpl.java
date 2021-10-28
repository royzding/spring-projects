package com.sample.microservices.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafKaProducerServiceImpl implements KafKaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafKaProducerServiceImpl.class);
     
    @Value("${spring.kafka.topic01-name}")
    private String topic01Name;
 
    @Value("${spring.kafka.topic02-name}")
    private String topic02Name;
 
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    public static final String TOPIC_NAME = "quickstart-events";

    private final KafkaTemplate<String, String> kafkaTemplate;
    
    KafKaProducerServiceImpl(final KafkaTemplate<String, String> kafkaTemplate) {
    	this.kafkaTemplate = kafkaTemplate;
    }
 
    public void sendMessageToTopic01(String message) 
    {
        System.out.println("----------------->>>>>" + "Message set:" + message);

        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(topic01Name, message);
        		
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

        	@Override
        	public void onSuccess(SendResult<String, String> result) {
        		System.out.println("Sent message=[" + message + 
        				"] with offset=[" + result.getRecordMetadata().offset() + "]");
        	}
        	
        	@Override
        	public void onFailure(Throwable ex) {
        	    System.out.println("Unable to send message=[" + message + 
        	    		"] due to : " + ex.getMessage());
        	}
        });
    	
    }
    
    public void sendMessageToTopic02(String message) 
    {
        System.out.println("----------------->>>>>" + "Message set:" + message);

        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(topic02Name, message);
        		
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

        	@Override
        	public void onSuccess(SendResult<String, String> result) {
        		System.out.println("Sent message=[" + message + 
        				"] with offset=[" + result.getRecordMetadata().offset() + "]");
        	}
        	
        	@Override
        	public void onFailure(Throwable ex) {
        	    System.out.println("Unable to send message=[" + message + 
        	    		"] due to : " + ex.getMessage());
        	}
        });
    	
    }
}