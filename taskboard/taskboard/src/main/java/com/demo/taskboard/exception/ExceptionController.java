package com.demo.taskboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.demo.taskboard.constants.ApplicationConstants;



@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler{
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> exception(ResourceNotFoundException exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ApplicationConstants.USER_NOT_FOUND);
		errorResponse.setStatusCode(ApplicationConstants.USER_NOT_FOUND_CODE);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = ResourceExistException.class)
	public ResponseEntity<ErrorResponse> exception(ResourceExistException exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ApplicationConstants.USER_FOUND);
		errorResponse.setStatusCode(ApplicationConstants.CONFLICT);
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}
	
}