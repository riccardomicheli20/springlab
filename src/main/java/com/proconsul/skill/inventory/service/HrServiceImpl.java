package com.proconsul.skill.inventory.service;

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

}
