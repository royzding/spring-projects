package com.sample.microservices.department.map;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import com.sample.microservices.common.model.HolidayDate;
import com.sample.microservices.department.data.model.HolidayDateEntity;
import com.sample.microservices.department.model.dto.HolidayDateDto;

@Component
@Mapper(componentModel="spring", nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
public interface HolidayDateMapper {
	
	HolidayDateEntity holidayDateDtoToEntity(HolidayDateDto source);
	
	List<HolidayDateEntity> holidayDateDtoToEntity(List<HolidayDateDto> source);
	
	HolidayDate entityToHolidayDate(HolidayDateEntity source);

	List<HolidayDate> entityToHolidayDate(List<HolidayDateEntity> source);

	@Mapping(target="id", source="id")		
	HolidayDate holidayDateDtoToHolidayDate(HolidayDateDto source);

}
