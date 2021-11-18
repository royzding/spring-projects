package com.sample.microservices.model.dto;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidEmployee extends Employee{
	
	@Pattern(regexp="([01]?[0-9]|2[0-3]):([0-5][0-9])(;([01]?[0-9]|2[0-3]):([0-5][0-9]))*", message = "Time format must be (HH:MM)(;HH:MM)*")
	private String time;

}