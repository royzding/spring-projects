package com.sample.microservices.employee.service;

import java.util.List;


import com.sample.microservices.employee.model.Employee;
import com.sample.microservices.model.dto.EmployeeDto;

public interface EmployeeService {

    Employee getEmployeeById(final Long id);
    
    List<Employee> getAllEmployees();
    
    Employee createEmployee(final EmployeeDto employeeDto);
    
    List<Employee> createEmployees(final List<EmployeeDto> employeeDto);
    
    void deleteEmployeeById(final Long id);
    
    void deleteAllEmployees();
    
    void updateEmployee(final Long id, final EmployeeDto employeeDto)  throws Exception;
    
    Long getCacheableTime();
}
