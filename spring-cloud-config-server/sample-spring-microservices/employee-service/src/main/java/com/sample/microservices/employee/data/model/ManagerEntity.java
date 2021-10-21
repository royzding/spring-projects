package com.sample.microservices.employee.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sample.microservices.common.data.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="manager")

@Getter
@Setter
public class ManagerEntity extends BaseEntity {
	
	private static final long serialVersionUID = 3L;
	
	@Column(name="salary")
	private Double salary;
	
}
