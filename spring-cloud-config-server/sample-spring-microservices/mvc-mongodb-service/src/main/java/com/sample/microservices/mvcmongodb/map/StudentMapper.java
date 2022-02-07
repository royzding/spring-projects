package com.sample.microservices.mvcmongodb.map;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import com.sample.microservices.common.data.model.StudentEntity;
import com.sample.microservices.common.model.dto.StudentDto;

@Component
@Mapper(componentModel="spring", nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {
	
	StudentEntity studentDtoToEntity(StudentDto source);	
	List<StudentEntity> studentDtoToEntity(List<StudentDto> source);
	
	StudentDto entityToStudentDto(StudentEntity source);

	List<StudentDto> entityToStudentDto(List<StudentEntity> source);

}
