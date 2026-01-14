package com.proconsul.skill.inventory.service;

import java.util.List;
import java.util.Map;

import com.proconsul.skill.inventory.dto.*;
import com.proconsul.skill.inventory.dto.HrRequestDto.HrLoginRequestDto;
import jakarta.validation.Valid;
import com.proconsul.skill.inventory.entity.Hr;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;

public interface HrService {

	List<EmployeeResponseDto> findAllEmployees();

	List<HrResponseDto> findAllHr();

	Map<String, Boolean> deleteEmployeeByFiscalCode(String fiscalCode) throws ResourceNotFoundException;

	public List<CategoryResponseDto> findAllCategories();

	public HrResponseUpdateDto updateHr(HrUpdateDto hrUpdateDto);

	public HrResponseUpdateDto patchHr(String email, HrPatchDto dto);

    SavedResponse saveCategory(@Valid SaveCategoryRequest saveCategoryRequest) ;

	HrResponseDto login(HrLoginRequestDto request);

	Map<String, Boolean> deleteHr(String email);

	HrResponseDto findHrByEmail(String email);

    HrResponseDto saveHr(Hr hr);

    SavedResponse saveTechnology(SaveTechnologyDto request) ;
}
