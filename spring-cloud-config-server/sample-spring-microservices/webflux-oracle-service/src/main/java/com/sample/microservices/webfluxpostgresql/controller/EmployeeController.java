package com.sample.microservices.webfluxpostgresql.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

import com.sample.microservices.webfluxpostgresql.model.Employee;
import com.sample.microservices.webfluxpostgresql.service.EmployeeServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employee")
@Tag(name = "Employee APIs", description = "APIs for Emplyee CRUD operations")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @GetMapping("/greeting")
    public String greet() {
	    List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");
	    Random rand = new Random();
	
	    int randomNum = rand.nextInt(greetings.size());
	    return greetings.get(randomNum);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Employee>> findEmpById(@PathVariable("id") Long id) {
        Mono<Employee> employee = employeeServiceImpl.findByEmpId(id);
        return new ResponseEntity<Mono<Employee>>(employee, employee != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Flux<Employee>> findEmpById(@PathVariable("name") String name) {
        Flux<Employee> employee = employeeServiceImpl.findByName(name);
        return new ResponseEntity<Flux<Employee>>(employee, employee != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    @ResponseBody
    public Flux<Employee> findAll() {
        return employeeServiceImpl.findAllEmp();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Employee> createEmp(@RequestBody Employee employee) {
        return employeeServiceImpl.createEmp(employee);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Employee> update(@RequestBody Employee employee) {
        return employeeServiceImpl.updateEmp(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        employeeServiceImpl.deleteEmp(id).subscribe();
    }

}
