package com.sample.microservices.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.microservices.batch.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
}
