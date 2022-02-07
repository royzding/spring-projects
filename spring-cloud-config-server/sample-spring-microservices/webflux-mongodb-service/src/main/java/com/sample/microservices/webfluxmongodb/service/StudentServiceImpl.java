package com.sample.microservices.webfluxmongodb.service;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.microservices.common.data.model.StudentEntity;
import com.sample.microservices.common.map.StudentMapper;
import com.sample.microservices.common.model.dto.StudentDto;
import com.sample.microservices.webfluxmongodb.repository.StudentEntityRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentEntityRepository studentRepo;
    
    private final StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);

	@Override
    public void createStudent(StudentDto studentDto) {
        studentRepo.save(this.studentMapper.studentDtoToEntity(studentDto)).subscribe();
    }

	@Override
    public Mono<StudentDto> findByStudentId(String id) {
        return this.studentMapper.studentEntityToStudentDto(this.studentRepo.findById(id));
    }

	@Override
    public Flux<StudentEntity> findAllStudentEntity() {
    	return this.studentRepo.findAll();
    }

	@Override
    public Flux<StudentDto> findAllStudent() {
    	return this.studentMapper.studentEntityToStudentDto(this.studentRepo.findAll());
    }

	@Override
    public Mono<StudentDto> updateStudent(String id, StudentDto studentDto) {
		StudentEntity studentEntity = this.studentMapper.studentDtoToEntity(studentDto);
		studentEntity.setId(id);
        return this.studentMapper.studentEntityToStudentDto(this.studentRepo.save(studentEntity));
    }

	@Override
    public Mono<Void> deleteStudent(String id) {
        return studentRepo.deleteById(id);
    }

	@Override
	public Flux<StudentEntity> findByLastName(String lastName) {
		return this.studentRepo.findByLastName(lastName);
	}

	@Override
	public Flux<StudentEntity> findByFirstNameContains(String firstName) {
		return this.studentRepo.findByFirstNameContains(firstName);
	}

}