package com.sample.microservices.department.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sample.microservices.common.model.Department;
import com.sample.microservices.department.service.DepartmentService;

@WebMvcTest(controllers=DepartmentController.class)
class DepartmentControllerTest extends BaseControllerTest{

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DepartmentService service;
	
	@Value("${auth.svcKey}")
	String svcKey;
	
	Map<String, String> requestHeaders;
	
	HttpHeaders httpHeaders;
	
	private Department department;
	
	List<Department> departments;
	
	static String department_data = "{\"id\": 10, \"name\": \"n10\"}";
	static String department_datas = "[{\"id\": 1, \"name\": \"n1\"},{\"id\": 2, \"name\": \"n2\"}]";
	
	@BeforeEach
	void setup() throws JsonMappingException, JsonProcessingException {
		
		
		requestHeaders = new HashMap<>();
		requestHeaders.put("X-SVC-KEY", svcKey);
		
		httpHeaders = new HttpHeaders();
		httpHeaders.setAll(requestHeaders);
		
		mapper.registerModule(new JavaTimeModule());
		
		department = mapper.readValue(department_data, Department.class);

		departments = mapper.readValue(department_datas, mapper.getTypeFactory()
				.constructCollectionLikeType(List.class, Department.class));
		
	}
	
	@Test
	void test_getDepartmentById() throws Exception {
		
		when(service.getDepartmentById(10L)).thenReturn(department);
		
		this.mockMvc.perform(get("/department-controller/10")
				.headers(httpHeaders)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(10)));
		
	}
	
	@Test
	void test_getAllDepartments() throws Exception {
		
		when(service.getAllDepartments()).thenReturn(departments);
		
		this.mockMvc.perform(get("/department-controller/all")
				.headers(httpHeaders)).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(departments.size())));
		
	}
	
	@Test
	void test_getAllDepartmentsWithEmpty() throws Exception {
		
		List<Department> departments = new ArrayList<>();
		
		when(service.getAllDepartments()).thenReturn(departments);
		
		this.mockMvc.perform(get("/department-controller/all")
				.headers(httpHeaders)).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(departments.size())));
		
	}
	
}
