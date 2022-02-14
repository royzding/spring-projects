package com.sample.microservices.webfluxpostgresql.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table     //@Table annotation is not necessary 
public class Employee {
    @Id
    private Long id;
    
    private String name;
    
    @Column("dep_id")
    private Integer depId;
    
    @Column("manager_id")
    private Integer managerId;
    
    private Double salary;
}
