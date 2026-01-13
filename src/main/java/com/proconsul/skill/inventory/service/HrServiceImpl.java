package com.proconsul.skill.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;
import com.proconsul.skill.inventory.dto.EmployeeResponseDto;
import com.proconsul.skill.inventory.dto.HrResponseDto;
import com.proconsul.skill.inventory.entity.Employee;
import com.proconsul.skill.inventory.entity.Hr;
import com.proconsul.skill.inventory.mapper.CategoryMapper;
import com.proconsul.skill.inventory.mapper.EmployeeMapper;
import com.proconsul.skill.inventory.mapper.HrMapper;
import com.proconsul.skill.inventory.repository.CategoryRepository;
import com.proconsul.skill.inventory.repository.EmployeeRepository;
import com.proconsul.skill.inventory.repository.HrRepository;

@Service
public class HrServiceImpl implements HrService {
	private CategoryMapper categoryMapper;
	private CategoryRepository categoryRepository;
	private EmployeeRepository employeeRepository;
	private EmployeeMapper employeeMapper;
	private HrRepository hrRepository;
	private HrMapper hrMapper;

	public HrServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository,
			EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, HrMapper hrMapper, HrRepository hrRepository ) {

		this.categoryMapper = categoryMapper;
		this.categoryRepository = categoryRepository;
		this.employeeMapper = employeeMapper;
		this.employeeRepository = employeeRepository;
		this.hrMapper = hrMapper;
		this.hrRepository = hrRepository;
	}

	@Override
	public List<CategoryResponseDto> findAllCategories() {
		return categoryMapper.toListCategoryResponseDto(categoryRepository.findAll());
	}

	@Override
	public List<EmployeeResponseDto> findAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();

		return employeeMapper.toEmployeeResponseDtoList(employees);
	}

	@Override
	public List<HrResponseDto> findAllHr() {

		List<Hr> hrs = hrRepository.findAll();
		return hrMapper.toHrResponseDtoList(hrs);
	}

}
