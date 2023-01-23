package com.sample.microservices.department.validator;

import com.sample.microservices.department.model.dto.HolidayDateDto;

public interface HolidayDateValidator {

	void validateHolidayDateUnique(HolidayDateDto holidayDateDto) throws Exception;
}
