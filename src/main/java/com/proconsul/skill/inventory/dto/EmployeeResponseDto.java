package com.proconsul.skill.inventory.dto;

import java.sql.Date;

public class EmployeeResponseDto {
	
	private String fiscalCode;
	private String firstName;
	private String lastName;
	private String email;
	private Date birthDate;
	private String birthPlace;
	private String address;
	private String city;
	private String postalCode;
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
	
	public EmployeeResponseDto(String birthPlace, Date birthDate, String email, String lastName, String firstName, String fiscalCode, String phoneNumber, String postalCode, String city, String address) {
		this.birthPlace = birthPlace;
		this.birthDate = birthDate;
		this.email = email;
		this.lastName = lastName;
		this.firstName = firstName;
		this.fiscalCode = fiscalCode;
		this.phoneNumber = phoneNumber;
		this.postalCode = postalCode;
		this.city = city;
		this.address = address;
	}
	
	public EmployeeResponseDto() {}
}