package com.globalsoftwaresupport.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.globalsoftwaresupport.model.ErrorMessage;

@ControllerAdvice
public class BaseExceptionHandler {

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleNotValidExeption(MethodArgumentNotValidException e) {
		var errors = e.getAllErrors();

		ErrorMessage message = null;

		if (errors != null && !errors.isEmpty()) {
			message = new ErrorMessage(400, errors.get(0).getDefaultMessage());
			return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
		}

		message = new ErrorMessage(400, "Bad Request");
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ NoSuchElementException.class, NumberFormatException.class })
	public ResponseEntity<ErrorMessage> handleNotFoundException(Exception e) {
		ErrorMessage message = new ErrorMessage(404, "Not Found");
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorMessage> handleIllegalException(IllegalArgumentException e) {
		ErrorMessage message = new ErrorMessage(400, e.getMessage());
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class })
	public ResponseEntity<ErrorMessage> handleNotReadableException(Exception e) {
		ErrorMessage message = new ErrorMessage(400, "Bad Request");
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}
}
