package com.proconsul.skill.inventory.dto;

import java.sql.Date;

public class EmployeePatchDto {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Date birthDate;
	
	private String birthPlace;
	
	private String address;
	
	private String city;
	
	private String postalCode;
	
	private String phoneNumber;
	
	public EmployeePatchDto() {}
	
	public EmployeePatchDto(String firstName, String lastName, String email, Date birthDate, String birthPlace, String address, String city, String postalCode, String phoneNumber) {
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
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getBirthPlace() {
		return birthPlace;
	}
	
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
