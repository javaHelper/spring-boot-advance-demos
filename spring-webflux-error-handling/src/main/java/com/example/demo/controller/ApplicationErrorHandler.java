package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.dto.ErrorResponse;
import com.example.demo.exception.StudentNotFoundException;

@ControllerAdvice
public class ApplicationErrorHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
		System.out.println(">> Controller Advice called for handleRuntimeException");
		var errorResponse = this.buildErrorResponse(100, "Unable to fetch students");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleStudentNotFoundException(StudentNotFoundException e) {
		System.out.println(">> Controller Advice called for handleStudentNotFoundException");
		var errorResponse = this.buildErrorResponse(101, String.format("Student id %s is not found", e.getStudentId()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	private ErrorResponse buildErrorResponse(int code, String message) {
		return new ErrorResponse(code, message);
	}
}