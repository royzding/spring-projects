package com.sample.microservices.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalRuntimeException extends RuntimeException {
	
	private static final long serialVersionUID = 8436014022103292703L;
	
	public InternalRuntimeException() {
		super("Internal Server Error.");
	}

	public InternalRuntimeException(String message) {
		super(message);
	}

	public InternalRuntimeException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
