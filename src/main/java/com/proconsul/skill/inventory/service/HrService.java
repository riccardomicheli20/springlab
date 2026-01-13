package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.dto.*;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;

import java.util.Map;
import java.util.List;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;
import com.proconsul.skill.inventory.dto.HrRequestDto.HrLoginRequestDto;
import com.proconsul.skill.inventory.dto.HrResponseDto;
import com.proconsul.skill.inventory.dto.SaveCategoryRequest;
import com.proconsul.skill.inventory.dto.SaveCategoryResponse;


public interface HrService {

	
    Map<String, Boolean> deleteEmployeeByFiscalCode(String fiscalCode) throws ResourceNotFoundException;

    public List<CategoryResponseDto> findAllCategories();

    public HrResponseUpdateDto updateHr(HrUpdateDto hrUpdateDto);

    public HrResponseUpdateDto patchHr(String email, HrPatchDto dto);

    SaveCategoryResponse saveCategory(SaveCategoryRequest saveCategoryRequest);

    HrResponseDto login(HrLoginRequestDto request);
}


