package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.dto.*;
import com.proconsul.skill.inventory.entity.Skill;
import com.proconsul.skill.inventory.exception.AccessNotValidException;
import com.proconsul.skill.inventory.mapper.EmployeeMapper;
import com.proconsul.skill.inventory.mapper.SkillMapper;
import com.proconsul.skill.inventory.repository.SkillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.proconsul.skill.inventory.entity.Employee;
import com.proconsul.skill.inventory.exception.EmployeeAlreadyExistException;
import com.proconsul.skill.inventory.exception.EntityNotFoundException;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;
import com.proconsul.skill.inventory.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	private final EmployeeRepository employeeRepository;
	private final EmployeeMapper employeeMapper;
	private final SkillMapper skillMapper;
	private final SkillRepository skillRepository;
	
	@Value("${entity.not.found}")
	private String entityNotFound;
	
	@Value("${not.valid.email}")
	private String errorMail;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, SkillMapper skillMapper, SkillRepository skillRepository, EmployeeMapper employeeMapper) {
		this.employeeRepository = employeeRepository;
		this.employeeMapper = employeeMapper;
		this.skillMapper = skillMapper;
		this.skillRepository = skillRepository;
	}
	
	@Override
	public EmployeeUpdateDto updateEmployee(EmployeeUpdateDto employeeUpdateDto) {
		
		log.info("Avvio aggiornamento dipendente con codice fiscale = {}", employeeUpdateDto.getFiscalCode());
		
		Employee existingEmployee = employeeRepository.findById(employeeUpdateDto.getFiscalCode()).orElseThrow(() -> {
			log.warn("Dipendente non trovato con codice fiscale = {}", employeeUpdateDto.getFiscalCode());
			return new ResourceNotFoundException("Dipendente non trovato con codice fiscale: " + employeeUpdateDto.getFiscalCode());
		});
		// converto dto in entity
		employeeMapper.toDto(employeeUpdateDto, existingEmployee);
		
		Employee saveEmployee = employeeRepository.save(existingEmployee);
		
		log.info("Aggiornamento completato con successo per fiscalCode={}", saveEmployee.getFiscalCode());
		
		employeeMapper.toEntity(saveEmployee, employeeUpdateDto);
		
		return employeeUpdateDto;
	}
	
	@Override
	public Employee saveEmployee(Employee employee) {
		
		Employee savedEmployee = null;
		
		if (employeeRepository.existsByFiscalCode(employee.getFiscalCode())) {
			throw new EmployeeAlreadyExistException("Employee with fiscal code: " + employee.getFiscalCode() + " " + " already exist");
		} else {
			
			try {
				
				savedEmployee = employeeRepository.save(employee);
				
			} catch (IllegalArgumentException | OptimisticLockingFailureException ex) {
				
				ex.getMessage();
			}
		}
		
		return savedEmployee;
	}
	
	@Override
	public EmployeePatchDto patchEmployee(String fiscalCode, EmployeePatchDto dto) {
		
		EmployeePatchDto dtoPatch = new EmployeePatchDto();
		
		Employee employee = employeeRepository.findByFiscalCode(fiscalCode).orElseThrow(
				() -> new EntityNotFoundException(entityNotFound + " with fiscal code " + fiscalCode + " not found"));
		
		employeeMapper.patchEmployeeFromDto(dto, employee);
			
		try {
			
			employeeMapper.patchEmployeeDtoFromEntity(employee, dtoPatch);
			employeeRepository.save(employee);
			
		} catch (IllegalArgumentException | OptimisticLockingFailureException ex) {
			
			ex.getMessage();
		}
		
		return dtoPatch;
		
	}
	
	@Override
	public LoginEmployeeDto loginEmployee(LoginEmployeeRequest loginEmployeeRequest) throws AccessNotValidException {
		
		Employee loggedEmployee = employeeRepository.findEmployeeByEmail(loginEmployeeRequest.getEmail()).orElseThrow(() -> new AccessNotValidException(errorMail));
		String fiscalCode = loggedEmployee.getFiscalCode();
		
		return new LoginEmployeeDto("Benvenuto!", fiscalCode);
		
	}
	
	@Override
	public List<SkillResponseDto> findAllSkills() {
		// Recupera tutte le skill dal DB
		List<Skill> skills = skillRepository.findAll();
		
		// Usa il mapper manuale per mappare technologyId e technologyName correttamente
		return skillMapper.toListSkillResponseDto(skills);
	}
	
	@Override
	public EmployeeResponseDto findByFiscalCode(String fiscalCode) throws ResourceNotFoundException {
		
		Employee employee = employeeRepository.findById(fiscalCode)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for fiscalCode: " + fiscalCode));
		
		return employeeMapper.toEmployeeResponseDto(employee);
	}
	
}

