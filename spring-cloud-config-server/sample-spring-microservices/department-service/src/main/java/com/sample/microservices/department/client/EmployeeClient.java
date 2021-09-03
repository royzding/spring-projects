package com.sample.microservices.department.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "employee-service")
public interface EmployeeClient {

	//@GetMapping("/department/{departmentId}")
	//List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId);
	
}
