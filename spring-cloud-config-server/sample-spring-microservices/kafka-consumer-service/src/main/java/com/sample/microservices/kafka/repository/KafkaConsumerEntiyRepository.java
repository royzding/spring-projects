package com.sample.microservices.kafka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.microservices.kafka.data.model.KafkaConsumerMessageEntity;

@Repository
public interface KafkaConsumerEntiyRepository extends JpaRepository<KafkaConsumerMessageEntity, Long>{
	
	List<KafkaConsumerMessageEntity> findByName(String name);
	List<KafkaConsumerMessageEntity> findByNameIn(List<String> name);
}
