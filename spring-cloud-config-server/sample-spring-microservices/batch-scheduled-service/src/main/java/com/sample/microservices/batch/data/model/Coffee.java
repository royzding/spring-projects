package com.sample.microservices.batch.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
	
    private String brand;
    private String origin;
    private String characteristics;
}
