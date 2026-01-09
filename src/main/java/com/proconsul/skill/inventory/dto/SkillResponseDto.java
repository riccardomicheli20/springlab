package com.proconsul.skill.inventory.dto;

import com.proconsul.skill.inventory.entity.Technology;
import com.proconsul.skill.inventory.enumerator.Level;
import com.proconsul.skill.inventory.enumerator.Seniority;

public class SkillResponseDto {

	private Long id;
	private Technology technology;
	private String categoryName;
	private Seniority seniority;
	private Level level;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Technology getTechnology() {
		return technology;
	}

	public void setTechnology(Technology technology) {
		this.technology = technology;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Seniority getSeniority() {
		return seniority;
	}

	public void setSeniority(Seniority seniority) {
		this.seniority = seniority;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	protected SkillResponseDto() {

	}

	public SkillResponseDto(Long id, Technology technology, String categoryName, Seniority seniority, Level level) {
		this.id = id;
		this.technology = technology;
		this.categoryName = categoryName;
		this.seniority = seniority;
		this.level = level;
	}

}
