package com.sample.microservices.webfluxoracledb.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.sample.microservices.webfluxoracledb.model.Employee;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
}