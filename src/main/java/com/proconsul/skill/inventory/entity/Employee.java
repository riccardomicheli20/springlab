package com.proconsul.skill.inventory.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Employee {

	@Pattern(regexp = "^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$", message = "Codice fiscale non valido")
	@Size(min = 16, max = 16)
	@Id
	@Column(name = "fiscal_code", length = 16)
	private String fiscalCode;

	@NotBlank
	@Column(name = "first_name", length = 60)
	private String firstName;

	@NotBlank
	@Column(name = "last_name", length = 60)
	private String lastName;

	@Column(length = 60)
	@Email
	@NotBlank
	private String email;

	@NotNull
	@Column(name = "birth_date")
	private Date birthDate;

	@NotBlank
	@Column(name = "birth_place", length = 58)
	private String birthPlace;

	@Column(length = 60)
	@NotBlank
	private String address;

	@Column(length = 60)
	@NotBlank
	private String city;

	@Column(length = 60)
	@NotBlank
	private String postalCode;

	@Column(length = 60)
	@NotBlank
	private String phoneNumber;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "employee_skill", joinColumns = @JoinColumn(name = "employee_fiscal_code"), inverseJoinColumns = @JoinColumn(name = "skill_id"))

	private List<Skill> skills;

	public String getFiscalCode() {
		return fiscalCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	protected Employee() {

	}

	public Employee(String fiscalCode, String firstName, String lastName, String email, Date birthDate,
			String birthPlace, String address, String city, String postalCode, String phoneNumber, List<Skill> skills) {
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
		this.skills = skills;
	}
}
