package com.sample.microservices.common.map;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import com.sample.microservices.common.data.model.StudentEntity;
import com.sample.microservices.common.model.dto.StudentDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Mapper(componentModel="spring", nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {
	
	StudentEntity studentDtoToEntity(StudentDto source);	
	List<StudentEntity> studentDtoToEntity(List<StudentDto> source);
	
	StudentDto entityToStudentDto(StudentEntity source);

	List<StudentDto> entityToStudentDto(List<StudentEntity> source);
	
    default Mono<StudentDto> studentEntityToStudentDto(Mono<StudentEntity> studentEntity){    	
    	return studentEntity.flatMap(x-> {
    		return Mono.just(this.entityToStudentDto(x));
    	});
    }    
    
    default Flux<StudentDto> studentEntityToStudentDto(Flux<StudentEntity> studentEntity){    	
    	return studentEntity.flatMap(x-> {
    		return Flux.just(this.entityToStudentDto(x));
    	});
    }    
	
    default List<StudentEntity> fluxToList(Flux<StudentEntity> studentEntity){    	
    	return studentEntity.collectList().block();
    }    
	

}
