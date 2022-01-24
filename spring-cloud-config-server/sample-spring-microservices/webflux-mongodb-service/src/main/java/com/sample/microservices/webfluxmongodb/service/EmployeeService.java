package com.sample.microservices.webfluxmongodb.service;

import com.sample.microservices.webfluxmongodb.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
public interface EmployeeService
{
    void createEmp(Employee e);
     
    Mono<Employee> findByEmpId(String id);
 
    Flux<Employee> findAllEmp();
 
    Mono<Employee> updateEmp(Employee e);
 
    Mono<Void> deleteEmp(String id);
}