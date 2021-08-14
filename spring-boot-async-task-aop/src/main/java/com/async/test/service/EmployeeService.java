package com.async.test.service;

import java.util.List;

import com.async.test.model.Employee;
import com.async.test.model.dto.EmployeeDto;

public interface EmployeeService {

    List<Employee> getAllEmployees();
    Employee getEmployee(final Long id);
    Employee createEmployee(final EmployeeDto employee);
    void deleteEmployee(final Long id);
    void updateEmployee(final Long id, final EmployeeDto employee);
}
