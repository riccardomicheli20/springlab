package com.proconsul.skill.inventory.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public class HrRequestDto {
	
	public static class HrLoginRequestDto {
		@Email
		@NotBlank
		private String email;

		@NotBlank
		private String password;
		
		public String getEmail() { return email; }
		public String getPassword() { return password; }
	}

}
