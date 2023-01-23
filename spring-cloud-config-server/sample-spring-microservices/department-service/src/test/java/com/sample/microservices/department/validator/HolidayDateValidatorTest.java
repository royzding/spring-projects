package com.sample.microservices.department.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sample.microservices.department.data.model.HolidayDateEntity;
import com.sample.microservices.department.map.HolidayDateMapper;
import com.sample.microservices.department.model.dto.HolidayDateDto;
import com.sample.microservices.department.repository.HolidayDateRepository;
import com.sample.microservices.department.service.BaseServiceTest;

public class HolidayDateValidatorTest extends BaseServiceTest {
	
	@MockBean
	private HolidayDateMapper mapper;
	
	@MockBean
	private HolidayDateRepository repository;
	
	@MockBean
	private HolidayDateValidator holidayDateValidator;
	
	@MockBean
	private HolidayDateEntity entity;
	
	@BeforeEach
	void setUp() throws JsonMappingException, JsonProcessingException {
		
		mapper = Mockito.mock(HolidayDateMapper.class);
		repository = Mockito.mock(HolidayDateRepository.class);
		
		holidayDateValidator = new HolidayDateValidatorImpl(repository);
		
	}
	
	@Test
	void test_validateHolidayDateUnique() {
		
		HolidayDateDto hdDto = new HolidayDateDto();
		hdDto.setName("nh1");
		hdDto.setDay(10);
		hdDto.setMonth(1);
		hdDto.setYear(2023);
		
		HolidayDateEntity hdEntity = new HolidayDateEntity();
		hdEntity.setName("nh1");
		hdEntity.setDay(10);
		hdEntity.setMonth(1);
		hdEntity.setYear(2023);
		
		when(this.repository.findByNameAndDayAndMonthAndYear(hdDto.getName(),hdDto.getDay(),hdDto.getMonth(), hdDto.getYear()))
		.thenReturn(Optional.of(hdEntity));
		
		Exception ex = assertThrows(Exception.class, ()->holidayDateValidator.validateHolidayDateUnique(hdDto));
		
		assertEquals(HolidayDateValidator.EXCEPTION_MSG, ex.getMessage());
	}
}