package com.sample.microservices.kafka.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.sample.microservices.kafka.map.EmployeeMapper;
import com.sample.microservices.kafka.repository.EmployeeRepository;
import com.sample.microservices.kafka.service.EmployeeService;
import com.sample.microservices.kafka.service.EmployeeServiceImpl;

@ActiveProfiles("unit")
@SpringBootTest
@AutoConfigureMockMvc
class TestAsyncControllerTest {
	
	EmployeeService employeeService;
	
	@Autowired 
	private MockMvc mvc;
	
	@MockBean
	private EmployeeRepository repository;
	
	private EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);
	
	@BeforeEach
	void setUp() {
		employeeService = new EmployeeServiceImpl(mapper, repository);

	}
	
	@Test
	void test_hello() throws Exception {
				
		mvc.perform(get("/")).andExpect(status().isOk()).andExpect(content().string("hello"));
	}
	

}
