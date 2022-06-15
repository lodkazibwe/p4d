package com.ruraaratech.p4dafrica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	// handle global exceptions
	/*@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request) {
		ErrorDetails errorDetails=new ErrorDetails(500, new Date(), request.getDescription(true), null);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}*/


	// handle specific exceptions
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(404, new Date(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidValuesException.class)
	public ResponseEntity<?> handleInvalidValuesException(InvalidValuesException exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(400, new Date(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyListException.class)
	public ResponseEntity<?> handleEmptyListException(EmptyListException exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(204, new Date(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NO_CONTENT);
	}
	

	/*@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String detail = ex.getMessage();

		String message = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()).toString();
		ErrorDetails errorDetails = new ErrorDetails(400, new Date(), message, detail);
		return new ResponseEntity<>(errorDetails, status);
	}*/

}
