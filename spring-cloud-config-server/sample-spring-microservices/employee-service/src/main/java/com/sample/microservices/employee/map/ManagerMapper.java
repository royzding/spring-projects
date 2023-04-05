package com.sample.microservices.employee.map;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import com.sample.microservices.common.model.Manager;
import com.sample.microservices.employee.data.model.ManagerEntity;
import com.sample.microservices.model.dto.IManagerMini;
import com.sample.microservices.model.dto.ManagerDto;
import com.sample.microservices.model.dto.ManagerMini;

@Component
@Mapper(componentModel="spring", nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
public interface ManagerMapper {
	
	@Mapping(target="name", source="name")		
	ManagerEntity managerDtoToEntity(ManagerDto source);
	
	List<ManagerEntity> managerDtoToEntity(List<ManagerDto> source);
	
	Manager entityToManager(ManagerEntity source);

	List<Manager> entityToManager(List<ManagerEntity> source);

	Manager managerDtoToManager(ManagerDto source);
	
	@Mapping(target="highSalary", expression="java(source.getSalary() > 999 ? true : false )")
	ManagerMini IManagerMiniToManagerMini(IManagerMini source);
	List<ManagerMini> IManagerMiniToManagerMini(List<IManagerMini> source);

}
