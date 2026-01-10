package com.proconsul.skill.inventory.dto;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmployeeUpdateDto {

	@Pattern(regexp = "^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$", message = "Codice fiscale non valido")
	@Size(min = 16, max = 16)
	private String fiscalCode;

	@NotBlank(message = "il campo è obbligatorio")
	private String firstName;

	@NotBlank(message = "il campo è obbligatorio")
	private String lastName;

	@Email
	@NotBlank(message = "il campo è obbligatorio")
	private String email;

	@NotNull(message = "il campo è obbligatorio")
	private Date birthDate;

	@NotBlank(message = "il campo è obbligatorio")
	private String birthPlace;

	@NotBlank(message = "il campo è obbligatorio")
	private String address;

	@NotBlank(message = "il campo è obbligatorio")
	private String city;

	@NotBlank(message = "il campo è obbligatorio")
	private String postalCode;

	@NotBlank(message = "il campo è obbligatorio")
	private String phoneNumber;

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public EmployeeUpdateDto(String fiscalCode, String firstName, String lastName, String email, Date birthDate,
			String birthPlace, String address, String city, String postalCode, String phoneNumber) {
		super();
		this.fiscalCode = fiscalCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
		this.birthPlace = birthPlace;
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
	}

	protected EmployeeUpdateDto() {

	}

}