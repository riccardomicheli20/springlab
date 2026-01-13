package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

import java.util.List;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;
import com.proconsul.skill.inventory.dto.HrRequestDto.HrLoginRequestDto;
import com.proconsul.skill.inventory.dto.HrResponseDto;

public interface HrService {
	
	Map<String, Boolean> deleteEmployeeByFiscalCode(String fiscalCode) throws ResourceNotFoundException;
	public List<CategoryResponseDto> findAllCategories();

	HrResponseDto login(HrLoginRequestDto request);
	
}


