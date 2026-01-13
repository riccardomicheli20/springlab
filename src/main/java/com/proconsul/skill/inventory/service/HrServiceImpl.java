package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;
import com.proconsul.skill.inventory.exception.EntityNotFoundException;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;
import com.proconsul.skill.inventory.mapper.CategoryMapper;
import com.proconsul.skill.inventory.repository.CategoryRepository;
import com.proconsul.skill.inventory.repository.EmployeeRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HrServiceImpl implements HrService{
	
	@Value("${entity.not.found}")
	private String entityNotFound;
	
    private CategoryMapper categoryMapper;
    private CategoryRepository categoryRepository;
	private final EmployeeRepository employeeRepository;
 
	public HrServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository, EmployeeRepository employeeRepository ) {
	this.categoryMapper = categoryMapper;
	this.categoryRepository = categoryRepository;
	this.employeeRepository = employeeRepository;
}


	@Override
	public List<CategoryResponseDto> findAllCategories() {
	    return categoryMapper.toListCategoryResponseDto(categoryRepository.findAll());
	}
	
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
