package com.sample.microservices.webfluxmongodb.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.sample.microservices.webfluxmongodb.model.Employee;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
}