package com.demo.taskboard.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String exception, HttpStatus status) {
		super(exception);
	}
}
