package com.sample.microservices.department.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sample.microservices.common.auth.config.SecurityConfiguration;

@ActiveProfiles({"unit", "api-security", "user-info"})
@AutoConfigureMockMvc
@ComponentScan({"com.sample.microservices.employee","com.sample.microservices.common"})
@Import(SecurityConfiguration.class)
class BaseControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Value("${auth.svcKey}")
	String svcKey;
	
	Map<String, String> requestHeaders;
	
	HttpHeaders httpHeaders;
	
	ObjectMapper mapper = new ObjectMapper();
	
	void baseSetup() {
		requestHeaders = new HashMap<>();
		requestHeaders.put("X-SVC-KEY", svcKey);
		
		httpHeaders = new HttpHeaders();
		httpHeaders.setAll(requestHeaders);
		
		mapper.registerModule(new JavaTimeModule());
	}
	
	@Test
	void test_default() {
		assertTrue(true);
	}
}
