package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.exception.ResourceNotFoundException;

import java.util.Map;
import java.util.List;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;
import com.proconsul.skill.inventory.dto.SaveCategoryRequest;
import com.proconsul.skill.inventory.dto.SaveCategoryResponse;

public interface HrService {

    Map<String, Boolean> deleteEmployeeByFiscalCode(String fiscalCode) throws ResourceNotFoundException;

    public List<CategoryResponseDto> findAllCategories();

    SaveCategoryResponse saveCategory(SaveCategoryRequest saveCategoryRequest);
}
