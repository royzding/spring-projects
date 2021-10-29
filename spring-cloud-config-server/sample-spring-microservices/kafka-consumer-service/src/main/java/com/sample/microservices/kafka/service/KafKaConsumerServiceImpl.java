package com.sample.microservices.kafka.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.sample.microservices.kafka.data.model.KafkaConsumerMessageEntity;
import com.sample.microservices.kafka.model.User;
import com.sample.microservices.kafka.repository.KafkaConsumerEntiyRepository;
 
@Service
public class KafKaConsumerServiceImpl implements KafKaConsumerService
{
    private final Logger logger = LoggerFactory.getLogger(KafKaConsumerServiceImpl.class);
    
    private final KafkaConsumerEntiyRepository kceRepository;
    
    KafKaConsumerServiceImpl(final KafkaConsumerEntiyRepository kceRepository) {
    	this.kceRepository = kceRepository;    	
    }

	@Override
	public void save(KafkaConsumerMessageEntity entity) {
		
		this.kceRepository.save(entity);
		
	}

	@Override
	public void save(String name, String topic, String group, String message) {
		
    	KafkaConsumerMessageEntity entity = new KafkaConsumerMessageEntity();
    	entity.setName(name);
    	entity.setTopic(topic);
    	entity.setGroup(group);
    	entity.setMessage(message);
    	entity.setCreatedDate(LocalDate.now());
    	
    	this.save(entity);
		
	}

	@Override
	public void save(String name, String topic, String group, String message, Integer partition, Integer offset) {

    	KafkaConsumerMessageEntity entity = new KafkaConsumerMessageEntity();
    	entity.setName(name);
    	entity.setTopic(topic);
    	entity.setGroup(group);
    	entity.setMessage(message);
    	entity.setPartition(partition);
    	entity.setOffset(offset);
    	entity.setCreatedDate(LocalDate.now());
    	
    	this.save(entity);
		
	}

	@Override
	public List<KafkaConsumerMessageEntity> getAllConsumerMessages() {
		
		return this.kceRepository.findAll();
	}

	@Override
	public void deleteAll() {
		this.kceRepository.deleteAll();		
	}
    
/*    
	@Override
    @KafkaListener(topics = "${spring.kafka.topic01-name}", groupId = "${spring.kafka.consumer.group01-id}")
    public void consume01OneTopic(String message) 
    {
        System.out.println("###################consume01OneTopic:" + "Message recieved:" + message);
    }
	
	@Override
    @KafkaListener(topics = "${spring.kafka.topic01-name}", groupId = "${spring.kafka.consumer.group01-id}")
    public void consume02OneTopic(String message) 
    {
        System.out.println("###################consume02OneTopic:" + "Message recieved:" + message);
    }
	
	@Override
    @KafkaListener(topics = "${spring.kafka.topic01-name}", groupId = "${spring.kafka.consumer.group02-id}")
    public void consume01T01G02(String message) 
    {
        System.out.println("###################consume01T01G02:" + "Message recieved:" + message);
    }
	
	@Override
    @KafkaListener(topics = "${spring.kafka.topic01-name}", groupId = "${spring.kafka.consumer.group02-id}")
    public void consume02T01G02(String message) 
    {
        System.out.println("###################consume02T01G02:" + "Message recieved:" + message);
    }
	
	@Override
	@KafkaListener(
			  topics = "${spring.kafka.topic01-name}", groupId = "${spring.kafka.consumer.group01-id}",  containerFactory = "filterKafkaListenerContainerFactory"
	)
	public void listenWithFilter(String message) {
		System.out.println("################### listenWithFilter: Received Message in filtered listener: " + message);
	}


	@Override
    @KafkaListener(topics = "${spring.kafka.topic02-name}", groupId = "${spring.kafka.consumer.group01-id}")
    public void consumeMultipleTopics(String message) 
    {
        System.out.println("###################consumeMultipleTopics:" + "Message recieved:" + message);
    }

	@Override
    @KafkaListener(topics = "${spring.kafka.topic03-name}", groupId = "${spring.kafka.consumer.group01-id}", containerFactory = "objectKafkaListenerContainerFactory")
    public void consumeWithObject(User user) 
    {
	        System.out.println("###################consumeWithObject:" + "User recieved:" + user);			
    }

	
    
    @KafkaListener(topics = "${spring.kafka.topic02-name}")
    public void listenWithHeaders(@Payload String message, 
    		@Header(KafkaHeaders.OFFSET) Integer offset,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) 
    {    	
    	logger.info("Processing topic = {}, partition = {}, offset = {}, message = {}",
                topic, partition, offset, message);         
        
        System.out.println("Received Message: " + message  + "from partition: " + partition);
    }
*/       
}