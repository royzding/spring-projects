package com.sample.microservices.employee.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.sample.microservices.common.data.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="employee")

@Getter
@Setter
public class EmployeeEntity extends BaseEntity {
	
	private static final long serialVersionUID = 2L;
	
	@Column(name="salary")
	private Double salary;
	
	@Column(name="dep_id")
	private Long depId;
	
	@OneToOne
	@JoinColumn(name="manager_id")
	private ManagerEntity managerEntity;
	
	
}
