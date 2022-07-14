package com.sample.microservices.employee.data.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.sample.microservices.common.data.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Department")

@Getter
@Setter
public class DepartmentEntity extends BaseEntity {
	
	private static final long serialVersionUID = 8436014022103292703L;
	
}
