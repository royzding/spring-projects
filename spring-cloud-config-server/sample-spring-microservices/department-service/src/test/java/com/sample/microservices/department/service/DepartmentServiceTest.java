package com.sample.microservices.department.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.microservices.common.model.Department;
import com.sample.microservices.department.data.model.DepartmentEntity;
import com.sample.microservices.department.employee.EmployeeService;
import com.sample.microservices.department.map.DepartmentMapper;
import com.sample.microservices.department.model.dto.DepartmentDto;
import com.sample.microservices.department.repository.DepartmentRepository;

class DepartmentServiceTest extends BaseServiceTest {
	
	private DepartmentMapper mapper;
	
	@Mock
	private DepartmentRepository repository;
	
	private EmployeeService employeeService;
	
	private DepartmentService departmentService;
	
	private DepartmentEntity entity;
	
	private List<DepartmentEntity> entities;
	
	static String department = "{\"id\": 10, \"name\": \"n10\"}";
	
	static String departments = "[{\"id\": 1, \"name\": \"n1\"},{\"id\": 2, \"name\": \"n2\"}]";
	
	@BeforeEach
	void setUp() throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objMapper = new ObjectMapper();
		
		mapper = Mappers.getMapper(DepartmentMapper.class);
		
		departmentService = new DepartmentServiceImpl(mapper, repository, employeeService);
		
		entity = objMapper.readValue(department, DepartmentEntity.class);
		
		entities = objMapper.readValue(departments, objMapper.getTypeFactory()
				.constructCollectionLikeType(List.class, DepartmentEntity.class));
	}
	
	@Test
	void test_getDepartmentById1() {
		
		DepartmentEntity dEntity = new DepartmentEntity();
		dEntity.setId(1L);
		dEntity.setName("depart_name");
		
		Mockito.when(this.repository.findById(1L)).thenReturn(Optional.ofNullable(dEntity));
		
		Department department = this.departmentService.getDepartmentById(1L);
		
		assertEquals(1L, department.getId());
		
	}

	@Test
	void test_getDepartmentById2() {
		
		Mockito.when(this.repository.findById(10L)).thenReturn(Optional.ofNullable(entity));
		
		Department department = this.departmentService.getDepartmentById(10L);
		
		assertEquals(10L, department.getId());
		
	}

	@Test
	void test_getAllDepartments() {
		
		DepartmentEntity dEntity = new DepartmentEntity();
		dEntity.setId(1L);
		dEntity.setName("depart_name");
		
		Mockito.when(this.repository.findAll()).thenReturn(entities);
		
		List<Department> departments = this.departmentService.getAllDepartments();
		
		assertEquals(2, departments.size());
		
	}

	@Test
	void test_createDepartment() {
		
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setId(10L);
		departmentDto.setName("d-name");
		
		DepartmentEntity entity = this.mapper.departmentDtoToEntity(departmentDto);

		Mockito.when(this.repository.save(entity)).thenReturn(entity);
		
		Department department = this.departmentService.createDepartment(departmentDto);
		
		assertEquals("d-name", department.getName());
		
	}

}
