package com.proconsul.skill.inventory.service;

import java.util.List;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;
import com.proconsul.skill.inventory.dto.EmployeeResponseDto;
import com.proconsul.skill.inventory.dto.HrResponseDto;

public interface HrService {
	public List<CategoryResponseDto> findAllCategories();

	List<EmployeeResponseDto> findAllEmployees();

	List<HrResponseDto> findAllHr();

}
