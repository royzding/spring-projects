package com.async.test.service;

import java.util.List;

import com.async.test.model.Employee;

public interface EmployeeService {

    List<Employee> getAllEmployees();
    Employee getEmployee(final Long id);
    Employee createEmployee(final Employee employee);
}
