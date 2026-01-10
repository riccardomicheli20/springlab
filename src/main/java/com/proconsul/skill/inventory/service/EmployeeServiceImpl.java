package com.proconsul.skill.inventory.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.proconsul.skill.inventory.dto.EmployeePatchDto;
import com.proconsul.skill.inventory.dto.EmployeeUpdateDto;
import com.proconsul.skill.inventory.entity.Employee;
import com.proconsul.skill.inventory.exception.EmployeeAlreadyExistException;
import com.proconsul.skill.inventory.exception.EntityNotFoundException;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;
import com.proconsul.skill.inventory.mapper.EmployeePatchDtoMapper;
import com.proconsul.skill.inventory.mapper.EmployeeUpdateMapper;
import com.proconsul.skill.inventory.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Value("${entity.not.found}")
	private String entityNotFound;
	
	private final EmployeeRepository employeeRepository;

	private final EmployeeUpdateMapper employeeUpdateMapper;
	
	private final EmployeePatchDtoMapper employeePatchDtoMapper;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeUpdateMapper employeeUpdateMapper,
			EmployeePatchDtoMapper employeePatchDtoMapper) {
		this.employeeRepository = employeeRepository;
		this.employeeUpdateMapper = employeeUpdateMapper;
		this.employeePatchDtoMapper = employeePatchDtoMapper;
	}

	@Override
	@Transactional
	public EmployeeUpdateDto updateEmployee(EmployeeUpdateDto employeeUpdateDto) {

		log.info("Avvio aggiornamento dipendente con codice fiscale = {}", employeeUpdateDto.getFiscalCode());

		Employee existingEmployee = employeeRepository.findById(employeeUpdateDto.getFiscalCode()).orElseThrow(() -> {
			log.warn("Dipendente non trovato con codice fiscale = {}", employeeUpdateDto.getFiscalCode());
			return new ResourceNotFoundException(
					"Dipendente non trovato con codice fiscale: " + employeeUpdateDto.getFiscalCode());
		});
		// converto dto in entity
		employeeUpdateMapper.toDto(employeeUpdateDto, existingEmployee);

		Employee saveEmployee = employeeRepository.save(existingEmployee);

		log.info("Aggiornamento completato con successo per fiscalCode={}", saveEmployee.getFiscalCode());

		employeeUpdateMapper.toEntity(saveEmployee, employeeUpdateDto);

		return employeeUpdateDto;
	}

    @Override
    public Employee saveEmployee(Employee employee) {
        if (employeeRepository.existsByFiscalCode(employee.getFiscalCode())) {
            throw new EmployeeAlreadyExistException("Employee with fiscal code: " + employee.getFiscalCode() + " " + " already exist");
        } else {
            return employeeRepository.save(employee);
        }
    }
	

	
	@Override
	public EmployeePatchDto patchEmployee(String fiscalCode, EmployeePatchDto dto) {
		
		Employee employee = employeeRepository.findByFiscalCode(fiscalCode)
				.orElseThrow(() -> new EntityNotFoundException(entityNotFound + " with fiscal code " + fiscalCode + " not found"));
		
		EmployeePatchDto updatedFields = new EmployeePatchDto();
		
		employeePatchDtoMapper.patchEmployeeFromDto(dto, employee);
		employeePatchDtoMapper.patchEmployeeDtoFromEntity(employee, updatedFields);
		
		employeeRepository.save(employee);
		
		return updatedFields;
	}
	

}
