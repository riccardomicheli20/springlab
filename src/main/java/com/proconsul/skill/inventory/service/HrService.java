package com.proconsul.skill.inventory.service;

import java.util.List;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;
import com.proconsul.skill.inventory.dto.HrResponseDto;
import com.proconsul.skill.inventory.dto.HrUpdateDto;

public interface HrService {
	public List<CategoryResponseDto> findAllCategories();

    public HrResponseDto updateHr(HrUpdateDto hrUpdateDto);

}
