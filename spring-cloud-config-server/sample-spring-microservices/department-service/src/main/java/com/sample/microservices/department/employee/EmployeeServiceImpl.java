package com.sample.microservices.department.employee;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sample.microservices.common.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeeApiFeignClient employeeFeignClientApi;
	
	EmployeeServiceImpl(EmployeeApiFeignClient employeeFeignClientApi) {
		this.employeeFeignClientApi = employeeFeignClientApi;
	}

	@Override
	public List<Employee> getEmployeesByDepartmentId(Long id) {
		return employeeFeignClientApi.findEmployeesByDepartmentId(id);
	}
	
	
}
