package com.proconsul.skill.inventory.entity;

import com.proconsul.skill.inventory.enumerator.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Hr {
	
	@Id
	@Email
	private String email;
	
	@NotBlank
	@Column(name = "first_name", length = 60)
	private String firstName;
	
	@NotBlank
	@Column(name = "last_name", length = 60)
	private String lastName;

	@NotBlank
	@Column(length = 32)
	@Size(min = 8, max = 32 )
	private String password;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public Hr(String email, String firstName, String lastName, String password, Role role) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
	}
	
	protected Hr() {
	
	}
	
	public @Email String getEmail() {
		return email;
	}
	
	public void setEmail(@Email String email) {
		this.email = email;
	}
	
	public @NotBlank Role getRole() {
		return role;
	}
	
	public void setRole(@NotBlank Role role) {
		this.role = role;
	}
	
	public @NotBlank @Size(min = 8, max = 32) String getPassword() {
		return password;
	}
	
	public void setPassword(@NotBlank @Size(min = 8, max = 32) String password) {
		this.password = password;
	}
	
	public @NotBlank String getLastName() {
		return lastName;
	}
	
	public void setLastName(@NotBlank String lastName) {
		this.lastName = lastName;
	}
	
	public @NotBlank String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(@NotBlank String firstName) {
		this.firstName = firstName;
	}
	
}
