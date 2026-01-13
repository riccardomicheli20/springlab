package com.proconsul.skill.inventory.service;

import java.util.List;

import com.proconsul.skill.inventory.dto.HrResponseDto;
import com.proconsul.skill.inventory.dto.HrUpdateDto;
import com.proconsul.skill.inventory.entity.Hr;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;
import com.proconsul.skill.inventory.mapper.HrMapper;
import com.proconsul.skill.inventory.repository.HrRepository;
import org.springframework.stereotype.Service;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;
import com.proconsul.skill.inventory.mapper.CategoryMapper;
import com.proconsul.skill.inventory.repository.CategoryRepository;

@Service
public class HrServiceImpl implements HrService{
 private CategoryMapper categoryMapper;
 private CategoryRepository categoryRepository;
 private HrRepository hrRepository;
 private HrMapper hrMapper;
 
	public HrServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository,HrRepository hrRepository,HrMapper hrMapper) {
	this.categoryMapper = categoryMapper;
	this.categoryRepository = categoryRepository;
    this.hrRepository = hrRepository;
    this.hrMapper = hrMapper;
}

	@Override
	public List<CategoryResponseDto> findAllCategories() {
	    return categoryMapper.toListCategoryResponseDto(categoryRepository.findAll());
	}

    @Override
    public HrResponseDto updateHr(HrUpdateDto hrUpdateDto) {
        Hr existHr = hrRepository.findById(hrUpdateDto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("Nessun HR trovato con questa email: " + hrUpdateDto.getEmail()));
        hrMapper.toDto(hrUpdateDto, existHr);
        Hr updatedHr = hrRepository.save(existHr);
        return hrMapper.toHrResponseDto(updatedHr);
    }

}
