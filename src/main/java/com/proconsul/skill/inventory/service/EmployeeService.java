package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.dto.*;
import com.proconsul.skill.inventory.entity.Employee;
import com.proconsul.skill.inventory.exception.AccessNotValidException;


import java.util.List;


public interface EmployeeService {
	
	EmployeeResponseDto updateEmployee(EmployeeUpdateDto employeePUpdateDto);
	
	EmployeeResponseDto patchEmployee(String fiscalCode, EmployeePatchDto employeePatchDto);

	EmployeeResponseDto saveEmployee(Employee employee);
	
	List<SkillResponseDto> findAllSkills();
	
	LoginEmployeeDto loginEmployee(LoginEmployeeRequest loginEmployeeRequest) throws AccessNotValidException;
	
	EmployeeResponseDto findByFiscalCode(String fiscalCode);
	
	List<EmployeeResponseDto> findAllEmployees();
	
}
	



