package com.sample.microservices.asyncaop.service;

import java.util.List;

import com.sample.microservices.asyncaop.model.Employee;
import com.sample.microservices.asyncaop.model.dto.EmployeeDto;

public interface EmployeeService {

    List<Employee> getAllEmployees();
    Employee getEmployee(final Long id);
    Employee createEmployee(final EmployeeDto employee);
    void deleteEmployee(final Long id);
    void updateEmployee(final Long id, final EmployeeDto employee);
    
    Long getCacheableTime();
}
