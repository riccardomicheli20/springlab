package com.proconsul.skill.inventory.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginEmployeeRequest {
	@Email(message = "inserisci un'email valida")
	@NotBlank(message = "Inserisci un'email")
	private String email;
	
	public LoginEmployeeRequest(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
