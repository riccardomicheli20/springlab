package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.dto.*;
import com.proconsul.skill.inventory.entity.Hr;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;

import java.util.Map;
import java.util.List;

public interface HrService {

    Map<String, Boolean> deleteEmployeeByFiscalCode(String fiscalCode) throws ResourceNotFoundException;

    public List<CategoryResponseDto> findAllCategories();

    public HrResponseUpdateDto updateHr(HrUpdateDto hrUpdateDto);

    public HrResponseUpdateDto patchHr(String email, HrPatchDto dto);

    SaveCategoryResponse saveCategory(SaveCategoryRequest saveCategoryRequest);

    HrResponseDto login(HrRequestDto.HrLoginRequestDto request);
    
    HrResponseDto saveHr(Hr hr);
}
