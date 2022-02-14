package com.sample.microservices.webfluxpostgresql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.microservices.webfluxpostgresql.model.Employee;
import com.sample.microservices.webfluxpostgresql.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepo;

    public Mono<Employee> findByEmpId(Long id) {
        return employeeRepo.findById(id);
    }

    public Flux<Employee> findAllEmp() {
    	return employeeRepo.findAll();
    }
   
    public Flux<Employee> findByName(String name) {
    	return employeeRepo.findByName(name);
    }
   
	//The employee ID needs to be null to indicate that the employee is new to create a new Employee
    public Mono<Employee> createEmp(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Mono<Employee> updateEmp(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Mono<Void> deleteEmp(Long id) {
        return employeeRepo.deleteById(id);
    }

}