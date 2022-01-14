package com.sample.microservices.department.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.sample.microservices.department.data.model.DepartmentEntity;
import com.sample.microservices.department.model.dto.DepartmentDto;

class DepartmentMapperTest {
	
	DepartmentMapper mapper = Mappers.getMapper(DepartmentMapper.class);
	
	@Test
	void test_departmentDtoToEntity() {
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setId(10L);
		departmentDto.setName("d-name");
		
		DepartmentEntity entity = mapper.departmentDtoToEntity(departmentDto);
		
		assertEquals(entity.getId(), departmentDto.getId());
		
	}

}
