package com.async.test.map;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import com.async.test.data.model.EmployeeEntity;
import com.async.test.model.Employee;
import com.async.test.model.dto.EmployeeDto;

@Component
@Mapper(componentModel="spring", nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {
	
	EmployeeDto employeeToEmployeeDto(Employee source);
	
	Employee employeeEntityToEmployee(EmployeeEntity source);

	List<Employee> employeeEntityToEmployee(List<EmployeeEntity> source);

	@Mappings({
		@Mapping(target="isActive", expression="java(source.getIsActive() == null ? true : source.getIsActive())"),
		@Mapping(target="id", source="id")		
	})
	Employee employeeDtoToEmployee(EmployeeDto source);

}
