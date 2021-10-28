package com.sample.microservices.kafka.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="employee")

@Getter
@Setter
public class EmployeeEntity {
	
	private static final long serialVersionUId = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name="firstname")
	private String firstName;
	
	@NotNull
	@Column(name="lastname")
	private String lastName;
	
	
	
	

}
