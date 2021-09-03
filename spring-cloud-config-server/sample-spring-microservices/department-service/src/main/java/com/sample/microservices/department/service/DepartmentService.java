package com.sample.microservices.department.service;

import java.util.List;

import com.sample.microservices.department.model.Department;
import com.sample.microservices.department.model.dto.DepartmentDto;

public interface DepartmentService {

    Department getDepartmentById(final Long id);
    
    List<Department> getAllDepartments();
    
    Department createDepartment(final DepartmentDto departmentDto);
    
    List<Department> createDepartments(final List<DepartmentDto> departmentDtos);
    
    void deleteDepartmentById(final Long id);
    
    void deleteAllDepartments();
    
    void updateDepartment(final Long id, final DepartmentDto departmentDto)  throws Exception;
    
    Long getCacheableTime();
}
