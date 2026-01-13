package com.proconsul.skill.inventory.dto;

public class CategoryResponseDto {
private String name;
private String technologyName;
public CategoryResponseDto(String name, String technologyName) {
	this.name = name;
	this.technologyName = technologyName;
}
protected CategoryResponseDto() {
	
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getTechnologyName() {
	return technologyName;
}
public void setTechnologyName(String technologyName) {
	this.technologyName = technologyName;
}





}
