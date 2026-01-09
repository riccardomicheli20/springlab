package com.proconsul.skill.inventory.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Technology {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable= false, unique= true, length=50)
	private String name;

	@ManyToOne(optional=false)
	private Category category;
	
	@OneToOne(mappedBy="technology")
	private Skill skill;
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected Technology() {

	}

	public Technology(@NotBlank String name, Category category) {
		this.name = name;
		this.category = category;
	}



}
