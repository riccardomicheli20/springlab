package com.proconsul.skill.inventory.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
}
