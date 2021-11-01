package com.sample.microservices.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.sample.microservices.kafka.service.KafKaConsumerService;
 
@Service
public class KafkaTopic04Pid2Consumer
{
    private final Logger logger = LoggerFactory.getLogger(KafkaTopic04Pid2Consumer.class);
    
    private final KafKaConsumerService kcService;
    
    KafkaTopic04Pid2Consumer(final KafKaConsumerService kcService) {
    	this.kcService = kcService;    	
    }

    @Value("${spring.kafka.topic04-name}")
    private String topic04Name;
    
    @Value("${spring.kafka.consumer.group05-id}")
    private String group05Id;
    
   //listening on TOPIC04 in GROUP05 and Pid2
    
    @KafkaListener(
        topicPartitions = 
        	{ @TopicPartition(topic = "${spring.kafka.topic04-name}", partitions = { "2" })},
            groupId = "${spring.kafka.consumer.group04-id}",
            autoStartup = "true")
    public void T4G5P2_consumer(@Payload String message){
    	this.kcService.save("T4G5P2_consumer", this.topic04Name, this.group05Id, message,2,0);    	
    	logger.info("T4G5P2_consumer: Message recieved:" + message);

    }   
    
    
	/*
	
	//For a topic with multiple partitions, however, a @KafkaListener can explicitly 
	//subscribe to a particular partition of a topic with an initial offset:
	
	@KafkaListener(
	  topicPartitions = @TopicPartition(topic = "topicName",
	  partitionOffsets = {
	    @PartitionOffset(partition = "0", initialOffset = "0"), 
	    @PartitionOffset(partition = "3", initialOffset = "0")}),
	  containerFactory = "partitionsKafkaListenerContainerFactory")
	public void listenToPartition(
	  @Payload String message, 
	  @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
	      System.out.println(
	        "Received Message: " + message"
	        + "from partition: " + partition);
	}
	
	//Since the initialOffset has been set to 0 in this listener, all the previously consumed messages 
	//from partitions 0 and 3 will be re-consumed every time this listener is initialized.
	
	*/

}