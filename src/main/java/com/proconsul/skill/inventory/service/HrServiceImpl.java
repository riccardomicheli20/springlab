package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.dto.*;
import com.proconsul.skill.inventory.entity.Employee;
import com.proconsul.skill.inventory.entity.Hr;
import java.util.List;

import com.proconsul.skill.inventory.dto.SaveCategoryRequest;
import com.proconsul.skill.inventory.dto.SaveCategoryResponse;
import com.proconsul.skill.inventory.entity.Category;
import com.proconsul.skill.inventory.exception.CategoryAlreadyExistException;
import com.proconsul.skill.inventory.exception.HrAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;
import com.proconsul.skill.inventory.exception.EntityNotFoundException;
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
	
    @Autowired
    private CategoryRepository prova;


    private CategoryMapper categoryMapper;
    private CategoryRepository categoryRepository;
	private HrRepository hrRepository;
	private EmployeeRepository employeeRepository;
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

        Hr existingHr = hrRepository.findById(dto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("Nessun HR trovato con email: " + dto.getEmail()));

        hrMapper.toDto(dto, existingHr);

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
	public HrResponseDto login(HrRequestDto.HrLoginRequestDto request) {
		Hr hr = hrRepository
				.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new RuntimeException("Credenziali non valide"));

		return new HrResponseDto(
				hr.getFirstName(),
				hr.getLastName(),
				hr.getEmail(),
				hr.getPassword(),
				hr.getRole()
		);
	}
	
	@Override
	public HrResponseDto saveHr(Hr hr) {
		
		if (hrRepository.existsById(hr.getEmail())) {
			throw new HrAlreadyExistException("Hr with email " + hr.getEmail() + " already exists");
		}
		
		Hr savedHr = null;
		
		try {
			savedHr = hrRepository.save(hr);
		} catch (DataIntegrityViolationException ex) {
			
			ex.getMessage();
		}
		
		return hrMapper.toHrResponseDto(savedHr);
		
	}
	
}
