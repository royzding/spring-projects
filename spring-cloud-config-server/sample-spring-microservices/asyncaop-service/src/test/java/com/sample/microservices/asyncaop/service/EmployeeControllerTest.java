package com.sample.microservices.asyncaop.service;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.sample.microservices.asyncaop.data.model.EmployeeEntity;
import com.sample.microservices.asyncaop.map.EmployeeMapper;
import com.sample.microservices.asyncaop.model.Employee;
import com.sample.microservices.asyncaop.repository.EmployeeRepository;
import com.sample.microservices.asyncaop.service.EmployeeService;
import com.sample.microservices.asyncaop.service.EmployeeServiceImpl;

@ActiveProfiles("unit")
@SpringBootTest
//@WebMvcTest(controllers = EmployeeController.class)
@AutoConfigureMockMvc

class EmployeeControllerTest { 
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private EmployeeService employeeService;
	
	@MockBean
	private EmployeeRepository repository;
	
	private EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);
	
	@BeforeEach
	void setUp() {
		employeeService = new EmployeeServiceImpl(mapper, repository);

	}
	
	@Test
	void test_getAllEmployeesx() throws Exception {
		
		EmployeeEntity entity = new EmployeeEntity();
		entity.setId(1L);
		entity.setFirstName("Fname");
		entity.setLastName("Lname");
		
		List<EmployeeEntity> entities = new ArrayList<>();
		entities.add(entity);
		
		when(this.repository.findAll()).thenReturn(entities);
		
		List<Employee> employees = this.employeeService.getAllEmployees();
		
		when(this.employeeService.getAllEmployees()).thenReturn(employees);
		
		System.out.println("=================" + employees.get(0).getFirstName());
		
		//not working.
		mvc.perform(get("/employee/all"))
			.andExpect(status().isOk()).andExpect(jsonPath("$.[0].firstName").value("Fname"));
		
		/*
			.andExpect(jsonPath("$.size()", is(employees.size())))
			.andExpect(jsonPath("$.[0].firstName", is("Fname")))
			;
		*/
		
	}

}
