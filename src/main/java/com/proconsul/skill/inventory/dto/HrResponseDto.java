package com.proconsul.skill.inventory.dto;

import com.proconsul.skill.inventory.enumerator.Role;


public class HrResponseDto {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Role role;

	public HrResponseDto(String firstName, String lastName, String email, String password, Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	protected HrResponseDto() {}

	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getEmail() { return email; }
	public String getPassword() { return password; }
	public Role getRole() { return role; }
}

	
	


