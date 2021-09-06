package com.sample.microservices.employee.map;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import com.sample.microservices.employee.data.model.EmployeeEntity;
import com.sample.microservices.employee.model.Employee;
import com.sample.microservices.model.dto.EmployeeDto;

@Component
@Mapper(componentModel="spring", nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {
	
	@Mapping(target="managerEntity.id", source="managerId")		
	EmployeeEntity employeeDtoToEntity(EmployeeDto source);
	
	List<EmployeeEntity> employeeDtoToEntity(List<EmployeeDto> source);
	
	@Mapping(target="managerId", source="managerEntity.id")		
	@Mapping(target="managerName", source="managerEntity.name")		
	Employee entityToEmployee(EmployeeEntity source);

	List<Employee> entityToEmployee(List<EmployeeEntity> source);

	@Mappings({
		@Mapping(target="id", source="id")		
	})
	Employee employeeDtoToEmployee(EmployeeDto source);

}
