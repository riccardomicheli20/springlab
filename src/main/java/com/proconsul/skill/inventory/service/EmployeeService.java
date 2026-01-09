package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.dto.EmployeePatchDto;
import com.proconsul.skill.inventory.entity.Employee;

public interface EmployeeService {
	
	public EmployeePatchDto patchEmployee(String fiscalCode, EmployeePatchDto employeePatchDto);
	
}
