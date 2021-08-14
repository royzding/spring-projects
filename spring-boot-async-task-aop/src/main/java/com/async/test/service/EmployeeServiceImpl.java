package com.async.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.async.test.model.Employee;
import com.async.test.model.dto.EmployeeDto;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	List<Employee> list = List.of(
			new Employee(1, "ef1", "el1"), 
			new Employee(2, "ef2", "el2"), 
			new Employee(3, "ef3", "el3")
			);

	@Override
	public List<Employee> getAllEmployees() {
		
		return this.list;
	}

	@Override
	public Employee getEmployee(Long id) {
		// TODO Auto-generated method stub
		return this.list.get(id.intValue()-1);
	}

	@Override
	public Employee createEmployee(EmployeeDto employee) {
		Employee emp = new Employee();
		emp.setId(employee.getId() + 1L);
		emp.setFirstName(employee.getFirstName()+"-new");
		emp.setLastName(employee.getLastName()+"-new");
		return emp;
	}

	@Override
	public void deleteEmployee(Long id) {
        System.out.println("Method deleteEmployee() called");
	}

	@Override
	public void updateEmployee(Long id, EmployeeDto employee) {
        System.out.println("Method updateEmployee() called");
	}
}
