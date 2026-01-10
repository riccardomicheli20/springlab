package com.proconsul.skill.inventory.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {	
	
	// in aplication properties non ce dove lo prendi?
	@Value("${entity.not.found}")
	private String entityNotFound;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleValidationException(MethodArgumentNotValidException ex) {

		String errorMessage = ex.getBindingResult().getFieldErrors().stream().map(this::formatFieldError)
				.collect(Collectors.joining("; "));

		ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), errorMessage);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleResourceNotFound(ResourceNotFoundException ex) {

		ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				ex.getMessage());

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleGenericException(Exception ex) {

		ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Unexpected error occurred");

		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private String formatFieldError(FieldError error) {
		return error.getField() + ": " + error.getDefaultMessage();
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EntityNotFoundException.class)
	public ErrorMessage handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
		
		return new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), entityNotFound);
		
	}	

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmployeeAlreadyExistException.class)
    public ErrorMessage handleEmployeeAlreadyExistException(EmployeeAlreadyExistException ex, HttpServletRequest request) {
        return new ErrorMessage(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Employee with that fiscal code already exist");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorMessage handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        return new ErrorMessage(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ErrorMessage handleOptimisticLockingFailureException(OptimisticLockingFailureException ex, HttpServletRequest request) {
        return new ErrorMessage(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

}
