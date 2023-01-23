package com.sample.microservices.department.validator;

import com.sample.microservices.department.model.dto.HolidayDateDto;

public interface HolidayDateValidator {

	String EXCEPTION_MSG = "Holiday Date Existed";
	
	void validateHolidayDateUnique(HolidayDateDto holidayDateDto) throws Exception;
}
