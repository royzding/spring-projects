package com.sample.microservices.employee.department;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sample.microservices.common.model.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private final DepartmentApiFeignClient departmentFeignClientApi;
	
	DepartmentServiceImpl(DepartmentApiFeignClient departmentFeignClientApi) {
		this.departmentFeignClientApi = departmentFeignClientApi;
	}

	@Override
	public Department getDepartmentById(Long id) {
		return departmentFeignClientApi.getDepartmentById(id);
	}
	
	@Override
	public List<Department> getAllDepartments() {
		return departmentFeignClientApi.getAllDepartments();
	}
	
	@Override
	public Map<Long,Department> getDepartmentMap() {
		return departmentFeignClientApi.getAllDepartments().stream().collect(Collectors.toMap(Department::getId, Function.identity()));
	}
	
	
}
