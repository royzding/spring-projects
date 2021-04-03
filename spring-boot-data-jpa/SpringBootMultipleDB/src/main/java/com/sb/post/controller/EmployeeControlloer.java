package com.sb.post.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.post.model.Employee;
import com.sb.post.service.EmployeeService;

@RestController
@RequestMapping(value="/employee")
public class EmployeeControlloer {
	
	@Autowired
	EmployeeService employeeService;
	

    @PostMapping("/save")  
    public Employee saveStudent(@RequestBody Employee employee) {  
         return employeeService.save(employee);  
          
    }  

	@GetMapping("/list") 
	public List<Employee> list() 
	{
		return employeeService.findAll();
	}
	
	@GetMapping("/get/{id}")
	public Employee get(@PathVariable("id") long id) {
		return employeeService.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") long id) {
		employeeService.delete(employeeService.findById(id));
	}
	
	@PostMapping("/update/{id}") 
	public Employee update(@RequestBody Employee employee,@PathVariable("id") long id) {
		employee.setId(id);  
		return employeeService.update(employee);
	}
	

}
