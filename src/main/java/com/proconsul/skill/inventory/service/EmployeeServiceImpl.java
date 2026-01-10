package com.proconsul.skill.inventory.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.proconsul.skill.inventory.dto.EmployeeUpdateDto;
import com.proconsul.skill.inventory.entity.Employee;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;
import com.proconsul.skill.inventory.mapper.EmployeeUpdateMapper;
import com.proconsul.skill.inventory.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	private final EmployeeRepository employeeRepository;

	private final EmployeeUpdateMapper employeeUpdateMapper;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeUpdateMapper employeeUpdateMapper) {
		this.employeeRepository = employeeRepository;
		this.employeeUpdateMapper = employeeUpdateMapper;
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

}
