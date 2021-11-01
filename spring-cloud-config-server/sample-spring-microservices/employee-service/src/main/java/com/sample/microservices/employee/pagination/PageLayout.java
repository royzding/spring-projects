package com.sample.microservices.employee.pagination;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PageLayout<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long totalElements;
	private int totalPages;
	private int firstElementNum;
	private List<T> data;
	
}
