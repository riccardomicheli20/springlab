package com.proconsul.skill.inventory.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@Value("${entity.not.found}")
	private String entityNotFound;
	
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
