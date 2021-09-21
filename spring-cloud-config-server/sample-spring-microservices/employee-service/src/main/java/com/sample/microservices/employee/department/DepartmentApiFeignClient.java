package com.sample.microservices.employee.department;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sample.microservices.common.model.Department;

@FeignClient(name = "department-service")//, url="http://localhost:8084/department-service")
public interface DepartmentApiFeignClient {

	@GetMapping("/department/{id}")
	Department getDepartmentById(@PathVariable("id") Long id);
	
	@GetMapping("/department/all")
	List<Department> getAllDepartments();
	
}
