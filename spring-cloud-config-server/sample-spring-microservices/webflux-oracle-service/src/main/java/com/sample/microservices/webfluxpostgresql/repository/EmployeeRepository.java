package com.sample.microservices.webfluxpostgresql.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.microservices.webfluxpostgresql.model.Employee;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Long> {
}