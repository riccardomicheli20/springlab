package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;
import com.proconsul.skill.inventory.dto.HrResponseDto;
import com.proconsul.skill.inventory.entity.Hr;
import com.proconsul.skill.inventory.exception.EntityNotFoundException;
import com.proconsul.skill.inventory.exception.HrAlreadyExistException;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;
import com.proconsul.skill.inventory.mapper.CategoryMapper;
import com.proconsul.skill.inventory.mapper.HrMapper;
import com.proconsul.skill.inventory.repository.CategoryRepository;
import com.proconsul.skill.inventory.repository.EmployeeRepository;
import com.proconsul.skill.inventory.repository.HrRepository;
import org.springframework.dao.OptimisticLockingFailureException;
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
	
	@Value("${not.valid.email}")
	private String errorMail;
	
    private CategoryMapper categoryMapper;
    private CategoryRepository categoryRepository;
	private final EmployeeRepository employeeRepository;
	private final HrRepository hrRepository;
	private final HrMapper hrMapper;
	
 
	public HrServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository, EmployeeRepository employeeRepository, HrRepository hrRepository, HrMapper hrMapper) {
	this.categoryMapper = categoryMapper;
	this.categoryRepository = categoryRepository;
	this.employeeRepository = employeeRepository;
	this.hrRepository = hrRepository;
	this.hrMapper = hrMapper;
}


	@Override
	public List<CategoryResponseDto> findAllCategories() {
	    return categoryMapper.toListCategoryResponseDto(categoryRepository.findAll());
	}
	
	@Override
	public HrResponseDto saveHr(Hr hr) {
		
		Hr savedHr = null;
		
		if (hrRepository.existsById(hr.getEmail())) {
			
			throw new HrAlreadyExistException("Hr with email " + hr.getEmail() + " already exists");
		
		} else {
				
				try {
					
					savedHr = hrRepository.save(hr);
					
				} catch (IllegalArgumentException | OptimisticLockingFailureException ex) {
					
					ex.getMessage();
				}
			}
			
			return hrMapper.toHrResponseDto(savedHr);
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
