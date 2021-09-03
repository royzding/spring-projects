package com.sample.microservices.asyncaop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.sample.microservices.asyncaop.data.model.EmployeeEntity;
import com.sample.microservices.asyncaop.map.EmployeeMapper;
import com.sample.microservices.asyncaop.model.Employee;
import com.sample.microservices.asyncaop.repository.EmployeeRepository;
import com.sample.microservices.asyncaop.service.EmployeeService;
import com.sample.microservices.asyncaop.service.EmployeeServiceImpl;

@ActiveProfiles("unit")
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
class EmployeeServiceTest { //extends BaseServiceTest {
	
	EmployeeService employeeService;
	
	@MockBean
	private EmployeeRepository repository;
	
	private EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);
	
	@BeforeEach
	void setUp() {
		employeeService = new EmployeeServiceImpl(mapper, repository);

	}
	
	@Test
	void test_getAllEmployees() {
		
		EmployeeEntity entity = new EmployeeEntity();
		entity.setId(1L);
		entity.setFirstName("Fname");
		entity.setLastName("Lname");
		
		List<EmployeeEntity> entities = new ArrayList<>();
		entities.add(entity);
		
		when(this.repository.findAll()).thenReturn(entities);
		
		List<Employee> employees = this.employeeService.getAllEmployees();
		
		System.out.println(employees);
		
		assertNotNull(employees);
		assertEquals(employees.get(0).getFirstName(), "Fname");
		
	}

}
