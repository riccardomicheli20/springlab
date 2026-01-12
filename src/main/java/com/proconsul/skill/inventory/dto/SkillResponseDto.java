package com.proconsul.skill.inventory.dto;

import com.proconsul.skill.inventory.enumerator.Level;
import com.proconsul.skill.inventory.enumerator.Seniority;

public class SkillResponseDto {
	
	private String technologyName;
	private String categoryName;
	private Seniority seniority;
	private Level level;
	
	public SkillResponseDto(String technologyName,  String categoryName, Seniority seniority, Level level) {
		this.technologyName = technologyName;
		this.level = level;
		this.seniority = seniority;
		this.categoryName = categoryName;
	}
	
	protected SkillResponseDto() {}
	
	public String getTechnologyName() {
		return technologyName;
	}
	
	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
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
}