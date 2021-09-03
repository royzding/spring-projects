package com.sample.microservices.asyncaop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
	
    private long id;
    private String firstName;
    private String lastName;
    
    private Boolean isActive;

}