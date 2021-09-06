package com.sample.microservices.employee.map;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import com.sample.microservices.common.model.Manager;
import com.sample.microservices.employee.data.model.ManagerEntity;
import com.sample.microservices.model.dto.ManagerDto;

@Component
@Mapper(componentModel="spring", nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
public interface ManagerMapper {
	
	ManagerEntity managerDtoToEntity(ManagerDto source);
	
	List<ManagerEntity> managerDtoToEntity(List<ManagerDto> source);
	
	Manager entityToManager(ManagerEntity source);

	List<Manager> entityToManager(List<ManagerEntity> source);

	Manager managerDtoToManager(ManagerDto source);

}
