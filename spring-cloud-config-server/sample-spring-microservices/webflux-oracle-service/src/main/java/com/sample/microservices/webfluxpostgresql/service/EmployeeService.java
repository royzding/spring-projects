package com.sample.microservices.webfluxpostgresql.service;

import com.sample.microservices.webfluxpostgresql.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
public interface EmployeeService
{
    Flux<Employee> findAllEmp();
/*
    void createEmp(Employee e);
     
    Mono<Employee> findByEmpId(String id);
 
 
    Mono<Employee> updateEmp(Employee e);
 
    Mono<Void> deleteEmp(String id);
    */
}