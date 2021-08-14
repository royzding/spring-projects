package com.async.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
    private long id;
    private String firstName;
    private String lastName;
    
    private Boolean isActive;

}
