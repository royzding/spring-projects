package com.sample.microservices.department.employee;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sample.microservices.common.model.Employee;

@FeignClient(name = "employee-service")//, url="http://localhost:8083/")
public interface EmployeeApiFeignClient {

	@GetMapping("/employee/employee-controller/employees/{departmentId}")
	List<Employee> findEmployeesByDepartmentId(@PathVariable("departmentId") Long departmentId);
	
}
