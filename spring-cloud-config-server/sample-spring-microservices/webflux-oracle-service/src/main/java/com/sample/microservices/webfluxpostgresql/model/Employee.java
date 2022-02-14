package com.sample.microservices.webfluxpostgresql.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Employee {
    @Id
    private Long id;
    private String name;
    private Double salary;
}
