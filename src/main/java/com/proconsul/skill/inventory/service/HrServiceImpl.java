package com.proconsul.skill.inventory.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;
import com.proconsul.skill.inventory.dto.EmployeeResponseDto;
import com.proconsul.skill.inventory.dto.HrPatchDto;
import org.springframework.beans.factory.annotation.Value;
import com.proconsul.skill.inventory.dto.HrRequestDto.HrLoginRequestDto;
import com.proconsul.skill.inventory.dto.HrResponseDto;
import com.proconsul.skill.inventory.dto.HrResponseUpdateDto;
import com.proconsul.skill.inventory.dto.HrUpdateDto;
import com.proconsul.skill.inventory.dto.SaveCategoryRequest;
import com.proconsul.skill.inventory.dto.SaveCategoryResponse;
import com.proconsul.skill.inventory.entity.Category;
import com.proconsul.skill.inventory.entity.Employee;
import com.proconsul.skill.inventory.entity.Hr;
import com.proconsul.skill.inventory.exception.CategoryAlreadyExistException;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;
import com.proconsul.skill.inventory.mapper.CategoryMapper;
import com.proconsul.skill.inventory.mapper.EmployeeMapper;
import com.proconsul.skill.inventory.mapper.HrMapper;
import com.proconsul.skill.inventory.repository.CategoryRepository;
import com.proconsul.skill.inventory.repository.EmployeeRepository;
import com.proconsul.skill.inventory.repository.HrRepository;

@Service
public class HrServiceImpl implements HrService {

	@Value("${entity.not.found}")
	private String entityNotFound;

	@Autowired
	private CategoryRepository prova;

	private CategoryMapper categoryMapper;
	private CategoryRepository categoryRepository;
	private EmployeeRepository employeeRepository;
	private EmployeeMapper employeeMapper;
	private HrRepository hrRepository;
	private HrMapper hrMapper;

	public HrServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository,
			EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, HrMapper hrMapper,
			HrRepository hrRepository) {

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

	@Override
	public SaveCategoryResponse saveCategory(SaveCategoryRequest saveCategoryRequest) {

		SaveCategoryResponse response = new SaveCategoryResponse();
		response.setSaved(false);
		try {

			Boolean found = prova.existsByName(saveCategoryRequest.getCategoryName());
			if (found) {
				throw new CategoryAlreadyExistException("Category with that name already exist");
			}
			Category savedCategory = new Category(saveCategoryRequest.getCategoryName(), null);
			prova.save(savedCategory);
			response.setSaved(true);
		} catch (IllegalArgumentException | OptimisticLockingFailureException e) {

			e.getMessage();
		}
		return response;

	}

	@Override
	public HrResponseUpdateDto updateHr(HrUpdateDto dto) {

		Hr existingHr = hrRepository.findById(dto.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("Nessun HR trovato con email: " + dto.getEmail()));

		hrMapper.updateHrFromDto(dto, existingHr);

		Hr updatedHr = hrRepository.save(existingHr);

		return hrMapper.toHrResponseUpdateDto(updatedHr);
	}

	@Override
    public HrResponseUpdateDto patchHr(String email, HrPatchDto dto) {
 
        HrResponseUpdateDto hrResponseUpdateDto = new HrResponseUpdateDto();
 
        HrPatchDto dtoPatch = new HrPatchDto();
 
        Hr hr = hrRepository.findById(email).orElseThrow(
                () -> new ResourceNotFoundException("Nessun HR trovato con email:" + email));
 
        hrMapper.patchHrFromDto(dto, hr);
 
        try {
 
            hrMapper.patchHrDtoFromEntity(hr, dtoPatch);
            hrRepository.save(hr);
            hrResponseUpdateDto = hrMapper.patchToResponseDto(dtoPatch);
 
        } catch (IllegalArgumentException | OptimisticLockingFailureException ex) {
 
            ex.getMessage();
        }
 
        return hrResponseUpdateDto;
 
    }

	@Override
	public HrResponseDto login(HrLoginRequestDto request) {
		Hr hr = hrRepository.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new RuntimeException("Credenziali non valide"));

		return new HrResponseDto(hr.getFirstName(), hr.getLastName(), hr.getEmail(), hr.getPassword(), hr.getRole());
	}

	@Override
	public Map<String, Boolean> deleteHr(String email) {

		Hr hr = hrRepository.findById(email)
				.orElseThrow(() -> new ResourceNotFoundException("HR not found with email: " + email));

		hrRepository.delete(hr);

		return Map.of("deleted", true);
	}

	@Override
	public HrResponseDto findHrByEmail(String email) {

		Hr hr = hrRepository.findById(email)
				.orElseThrow(() -> new ResourceNotFoundException("HR not found with email: " + email));

		return hrMapper.toHrResponseDto(hr);
	}

	@Override
	public Map<String, Boolean> deleteEmployeeByFiscalCode(String fiscalCode) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
