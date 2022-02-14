package com.sample.microservices.webfluxpostgresql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.microservices.webfluxpostgresql.model.Employee;
import com.sample.microservices.webfluxpostgresql.repository.EmployeeRepository;

import reactor.core.publisher.Flux;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepo;

    public Flux<Employee> findAllEmp() {
    	Flux<Employee> emps = employeeRepo.findAll();
    	return emps;
    }

/*    
    public void createEmp(Employee employee) {
        employeeRepo.save(employee).subscribe();
    }

    public Mono<Employee> findByEmpId(String id) {
        return employeeRepo.findById(id);
    }


    public Mono<Employee> updateEmp(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Mono<Void> deleteEmp(String id) {
        return employeeRepo.deleteById(id);
    }
*/
}