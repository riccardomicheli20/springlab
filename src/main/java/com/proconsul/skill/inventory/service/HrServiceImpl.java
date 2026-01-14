package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.dto.*;
import com.proconsul.skill.inventory.entity.*;

import java.util.List;
import java.util.Map;

import com.proconsul.skill.inventory.exception.*;
import com.proconsul.skill.inventory.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.proconsul.skill.inventory.repository.TechnologyRepository;
import jakarta.validation.Valid;
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
import com.proconsul.skill.inventory.entity.Category;
import com.proconsul.skill.inventory.entity.Employee;
import com.proconsul.skill.inventory.entity.Hr;
import com.proconsul.skill.inventory.exception.CategoryAlreadyExistException;
import com.proconsul.skill.inventory.mapper.CategoryMapper;
import com.proconsul.skill.inventory.mapper.HrMapper;
import com.proconsul.skill.inventory.repository.CategoryRepository;
import com.proconsul.skill.inventory.repository.EmployeeRepository;
import com.proconsul.skill.inventory.repository.HrRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

@Service
public class HrServiceImpl implements HrService {


    @Value("${entity.not.found}")
    private String entityNotFound;

    @Value("${not.valid.email}")
    private String errorMail;


    private CategoryMapper categoryMapper;
    private CategoryRepository categoryRepository;
	private HrRepository hrRepository;
	private EmployeeRepository employeeRepository;
    private final HrMapper hrMapper;
    private EmployeeMapper employeeMapper;
    private final TechnologyRepository technologyRepository;

    public HrServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository, EmployeeRepository employeeRepository, HrRepository hrRepository, HrMapper hrMapper, TechnologyRepository technologyRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
        this.employeeRepository = employeeRepository;
        this.hrRepository = hrRepository;
        this.hrMapper = hrMapper;
		this.employeeMapper = employeeMapper;
        this.technologyRepository = technologyRepository;
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
    public SavedResponse saveCategory(@Valid SaveCategoryRequest saveCategoryRequest)  {

        SavedResponse response = new SavedResponse();
        response.setSaved(false);
        try {

            Boolean found = categoryRepository.existsByName(saveCategoryRequest.getCategoryName());
            if (found) {
                throw new CategoryAlreadyExistException("Category with that name already exist");
            }
            Category savedCategory = new Category(saveCategoryRequest.getCategoryName(), null);
            categoryRepository.save(savedCategory);
            response.setSaved(true);
        } catch (IllegalArgumentException | OptimisticLockingFailureException | CategoryAlreadyExistException e) {

            e.getMessage();
        }
        return response;

    }

    @Override
    public HrResponseUpdateDto updateHr(HrUpdateDto dto) {

        Hr existingHr = hrRepository.findById(dto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("Nessun HR trovato con email: " + dto.getEmail()));

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

    @Override
    public SavedResponse saveTechnology(SaveTechnologyDto request) {
        SavedResponse savedResponse = new SavedResponse(false);

        if (technologyRepository.existsByName(request.getTechnologyName())){
            throw new TechnologyAlreadyExistException("technology with name -"+request.getTechnologyName()+ " already exists.");
        }
        Category category = categoryRepository.findCategoryByName(request.getCategoryName()).orElseThrow(() -> new EntityNotFoundException(entityNotFound));
        try {
            Technology savedTechnology = new Technology(request.getTechnologyName(), category);
            technologyRepository.save(savedTechnology);
            savedResponse.setSaved(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return savedResponse;


    }

}
