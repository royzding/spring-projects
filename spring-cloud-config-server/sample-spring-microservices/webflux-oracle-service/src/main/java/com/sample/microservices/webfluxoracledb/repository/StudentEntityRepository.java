package com.sample.microservices.webfluxoracledb.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.sample.microservices.common.data.model.StudentEntity;

import reactor.core.publisher.Flux;

@Repository
public interface StudentEntityRepository extends ReactiveMongoRepository<StudentEntity, String> {
	
	Flux<StudentEntity> findByLastName(String lastName);
	
	Flux<StudentEntity> findByFirstNameContains(String firstName);
		
}