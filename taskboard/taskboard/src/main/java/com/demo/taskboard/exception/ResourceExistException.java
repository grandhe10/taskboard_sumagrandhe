package com.demo.taskboard.exception;

import org.springframework.http.HttpStatus;

public class ResourceExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceExistException(String exception, HttpStatus status) {
		super(exception);
	}
}
