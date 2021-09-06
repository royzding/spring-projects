package com.sample.microservices.employee.department;

import java.util.List;
import java.util.Map;

import com.sample.microservices.common.model.Department;

public interface DepartmentService {

	Department getDepartmentById(final Long id);
	
	List<Department> getAllDepartments();
	
	Map<Long,Department> getDepartmentMap();
}
