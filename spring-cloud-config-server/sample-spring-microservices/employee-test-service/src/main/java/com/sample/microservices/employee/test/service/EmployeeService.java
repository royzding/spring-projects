package com.sample.microservices.employee.test.service;

import java.util.List;

import com.sample.microservices.employee.test.model.EmployeeEntity;
 
public interface EmployeeService
{
    List<EmployeeEntity> getAllEmployees();
    
}