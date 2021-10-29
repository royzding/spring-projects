package com.sample.microservices.kafka.service;

import java.util.List;

import com.sample.microservices.kafka.data.model.KafkaConsumerMessageEntity;

public interface KafKaConsumerService {
	
	List<KafkaConsumerMessageEntity> getAllConsumerMessages();

	void save(KafkaConsumerMessageEntity entity);
	void save(String name, String topic, String group, String message);
	void save(String name, String topic, String group, String message, Integer partition, Integer offset);
	
	void deleteAll();
	

/*	
	void consume01OneTopic(String message);
	void consume02OneTopic(String message);
	void consume01T01G02(String message);
	void consume02T01G02(String message);
	void consumeMultipleTopics(String message);
	
	void listenWithFilter(String message);
	
	void consumeWithObject(User user);	
*/	
}
