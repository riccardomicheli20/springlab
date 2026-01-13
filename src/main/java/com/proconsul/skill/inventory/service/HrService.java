package com.proconsul.skill.inventory.service;

import java.util.List;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;

public interface HrService {
	public List<CategoryResponseDto> findAllCategories();

}
