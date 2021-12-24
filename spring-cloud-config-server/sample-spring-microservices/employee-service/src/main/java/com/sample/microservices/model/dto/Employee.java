package com.sample.microservices.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	@NotNull(message = "NotNull must be not null. An empty value, however, is perfectly legal.")
	private String nameNotNull;
	
	@NotEmpty(message = "NotEmpty must be not null, and its size/length must be greater than zero")
	private String nameNotEmpty;
	
	@NotBlank(message = "NotBlank must be not null, and the trimmed length must be greater than zero.")
	private String nameNotBlank;
	
	@NotBlank(message = "Name must not be blank.")
	@Pattern(regexp="[a-zA-Z0-9@#%&:;,~`_\"\'\s\\!\\$\\^\\*\\(\\)\\+\\-\\=\\{\\}\\|\\[\\]\\<\\>\\.\\?\\/\\\\]+", 
	message = "name must be the following characters [a-zA-Z0-9@#%&:;,~`_\"'\s!$^*()+_={}|[]<>.?/\\]")
	private String name;
	
	@NotBlank(message = "Name must not be blank.")
	@Pattern(regexp="[\s-~]+", 
	message = "name must be the following characters [a-zA-Z0-9@#%&:;,~`_\"'\s!$^*()+_={}|[]<>.?/\\]")
	private String simplename;
	
}