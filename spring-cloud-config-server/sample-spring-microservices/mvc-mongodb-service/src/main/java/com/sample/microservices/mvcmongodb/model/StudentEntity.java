package com.sample.microservices.mvcmongodb.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sample.microservices.mvcmongodb.enums.Gender;
import com.sample.microservices.mvcmongodb.model.dto.Address;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Student")
@Data
@NoArgsConstructor
public class StudentEntity {
	@Id
	private String id;

	private String firstName;
	private String lastName;
	private boolean isActive;
	private Gender gender;
	private String email;
	private Address address;
	private List<String> courses;
	private BigDecimal salary;
	private LocalTime created;
  
	public StudentEntity(String firstName, String lastName, boolean isActive, Gender gender, List<String> courses,
			BigDecimal salary, LocalTime created) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.isActive = isActive;
		this.gender = gender;
		this.courses = courses;
		this.salary = salary;
		this.created = created;
	}
 

}
