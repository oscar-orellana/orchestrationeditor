package com.udc.fic.orchestrationeditor.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.udc.fic.orchestrationeditor.exception.DuplicatedUserException;
import com.udc.fic.orchestrationeditor.exception.InputValidationException;
import com.udc.fic.orchestrationeditor.exception.UserNotFoundException;
import com.udc.fic.orchestrationeditor.util.ErrorCode;
import com.udc.fic.orchestrationeditor.util.ErrorResponse;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DuplicatedUserException.class)
	public ResponseEntity<ErrorResponse> handleDuplicatedUserException(Exception ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.DUPLICATED_USER, ex.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.USER_NOT_FOUND, ex.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InputValidationException.class)
	public ResponseEntity<ErrorResponse> handleInputValidationException(Exception ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INPUT_VALIDATION, ex.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		if (ex.getMessage().contains("UserRole")) {
			errorResponse.setCode(ErrorCode.INPUT_VALIDATION);
			errorResponse.setMessage("Invalid role value");
		}
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
}
