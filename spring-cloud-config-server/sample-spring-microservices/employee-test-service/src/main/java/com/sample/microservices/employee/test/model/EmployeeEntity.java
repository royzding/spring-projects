package com.sample.microservices.employee.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="employee")

@Data
@Getter
@Setter
public class EmployeeEntity {
	
	  private static final long serialVersionUID = 2L;
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  
	  @Column(name = "dep_id")
	  private Integer depId;
	    
	  @Column(name = "manager_id")
	  private Integer managerId;
	    
	  @Column(name = "salary")
	  private Double salary;
	
}
