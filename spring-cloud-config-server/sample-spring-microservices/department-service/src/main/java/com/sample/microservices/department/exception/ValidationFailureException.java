package com.sample.microservices.department.exception;

public class ValidationFailureException extends RuntimeException {
	
	private static final long serialVersionUID = 8436014022103292703L;
	
	public ValidationFailureException() {
		super("Validatin Failure.");
	}

	public ValidationFailureException(String message) {
		super(message);
	}

	public ValidationFailureException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
