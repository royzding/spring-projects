package com.sample.microservices.webfluxmongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.webfluxmongodb.model.Employee;
import com.sample.microservices.webfluxmongodb.service.EmployeeServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employee")
@Tag(name = "Employee APIs", description = "APIs for Emplyee CRUD operations")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @GetMapping("/all")
    @ResponseBody
    public Flux<Employee> findAll() {
        return employeeServiceImpl.findAllEmp();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Employee>> findEmpById(@PathVariable("id") String id) {
        Mono<Employee> employee = employeeServiceImpl.findByEmpId(id);
        return new ResponseEntity<Mono<Employee>>(employee, employee != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmp(@RequestBody Employee employee) {
        employeeServiceImpl.createEmp(employee);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Employee> update(@RequestBody Employee employee) {
        return employeeServiceImpl.updateEmp(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) {
        employeeServiceImpl.deleteEmp(id).subscribe();
    }

}
