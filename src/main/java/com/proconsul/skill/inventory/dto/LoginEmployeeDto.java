package com.proconsul.skill.inventory.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginEmployeeDto {
	private String message;
	
	private String fiscalCode;
	
	protected LoginEmployeeDto() {
	}
	
	public LoginEmployeeDto(String message, String fiscalCode) {
		this.message = message;
		this.fiscalCode = fiscalCode;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getFiscalCode() {
		return fiscalCode;
	}
	
	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}
}
