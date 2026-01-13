package com.proconsul.skill.inventory.service;

import java.util.List;
import java.util.Map;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;
import com.proconsul.skill.inventory.dto.HrPatchDto;
import com.proconsul.skill.inventory.dto.HrRequestDto.HrLoginRequestDto;
import com.proconsul.skill.inventory.dto.HrResponseDto;
import com.proconsul.skill.inventory.dto.HrResponseUpdateDto;
import com.proconsul.skill.inventory.dto.HrUpdateDto;
import com.proconsul.skill.inventory.dto.SaveCategoryRequest;
import com.proconsul.skill.inventory.dto.SaveCategoryResponse;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;

public interface HrService {

	Map<String, Boolean> deleteEmployeeByFiscalCode(String fiscalCode) throws ResourceNotFoundException;

	public List<CategoryResponseDto> findAllCategories();

	public HrResponseUpdateDto updateHr(HrUpdateDto hrUpdateDto);

	public HrResponseUpdateDto patchHr(String email, HrPatchDto dto);

	SaveCategoryResponse saveCategory(SaveCategoryRequest saveCategoryRequest);

	HrResponseDto login(HrLoginRequestDto request);

	Map<String, Boolean> deleteHr(String email);

	HrResponseDto findHrByEmail(String email);
}
