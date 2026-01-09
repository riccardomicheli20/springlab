package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.dto.EmployeePatchDto;
import com.proconsul.skill.inventory.entity.Employee;
import com.proconsul.skill.inventory.exception.EntityNotFoundException;
import com.proconsul.skill.inventory.mapper.EmployeePatchDtoMapper;
import com.proconsul.skill.inventory.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private final EmployeeRepository employeeRepository;
	private final EmployeePatchDtoMapper employeePatchDtoMapper;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeePatchDtoMapper employeePatchDtoMapper) {
		this.employeeRepository = employeeRepository;
		this.employeePatchDtoMapper = employeePatchDtoMapper;
	}
	
	@Value("${entity.not.found}")
	private String entityNotFound;
	
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
