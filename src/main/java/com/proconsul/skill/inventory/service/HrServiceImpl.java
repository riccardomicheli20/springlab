package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.exception.EntityNotFoundException;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;
import com.proconsul.skill.inventory.repository.EmployeeRepository;
import com.proconsul.skill.inventory.repository.HrRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
import java.util.List;

import org.springframework.stereotype.Service;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;
import com.proconsul.skill.inventory.mapper.CategoryMapper;
import com.proconsul.skill.inventory.repository.CategoryRepository;

@Service
public class HrServiceImpl implements HrService{
 private CategoryMapper categoryMapper;
 private CategoryRepository categoryRepository;
 
	public HrServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
	
	this.categoryMapper = categoryMapper;
	this.categoryRepository = categoryRepository;
}

	@Override
	public List<CategoryResponseDto> findAllCategories() {
	    return categoryMapper.toListCategoryResponseDto(categoryRepository.findAll());
	}

	
	@Value("${entity.not.found}")
	private String entityNotFound;
	
	private final EmployeeRepository employeeRepository;
	
	public HrServiceImpl(EmployeeRepository employeeRepository) {this.employeeRepository = employeeRepository;}
	
	@Transactional
	@Override
	public Map<String, Boolean> deleteEmployeeByFiscalCode(String fiscalCode) throws ResourceNotFoundException {
		
		if (!employeeRepository.existsByFiscalCode(fiscalCode)) {
			
			throw new EntityNotFoundException(entityNotFound + " with fiscal code " + fiscalCode + " not found");
		}
		
		employeeRepository.deleteEmployeeByFiscalCode(fiscalCode);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", true);
		
		return response;
		
	}
}
