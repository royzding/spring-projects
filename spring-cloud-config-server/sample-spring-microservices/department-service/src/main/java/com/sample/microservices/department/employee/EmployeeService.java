package com.sample.microservices.department.employee;

import java.util.List;

import com.sample.microservices.department.model.Employee;

public interface EmployeeService {

     List<Employee> getEmployeesByDepartmentId(final Long id);
}
