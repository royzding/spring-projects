package com.sample.microservices.department.data.model;

import java.io.Serializable;

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
@Table(name="Department")

@Getter
@Setter
public class DepartmentEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8436014022103292703L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name="name")
	private String name;
	
}
