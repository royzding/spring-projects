package com.sample.microservices.employee.department;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sample.microservices.common.model.Department;

//@FeignClient(name = "DEPARTMENT-SERVICE")//, url="http://localhost:8084/")
@FeignClient(name = "DEPARTMENT-SERVICE", url="http://localhost:8084/")
public interface DepartmentApiFeignClient {

	@GetMapping("/department/department-controller/{id}")
	Department getDepartmentById(@PathVariable("id") Long id);
	
	@GetMapping("/department/department-controller/all")
	List<Department> getAllDepartments();
	
}
