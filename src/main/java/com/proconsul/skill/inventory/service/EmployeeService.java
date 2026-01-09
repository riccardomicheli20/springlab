package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.entity.Employee;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

import com.proconsul.skill.inventory.dto.EmployeePatchDto;
import com.proconsul.skill.inventory.entity.Employee;

public interface EmployeeService {
	
	public EmployeePatchDto patchEmployee(String fiscalCode, EmployeePatchDto employeePatchDto);
	

    public Employee saveEmployee(Employee employee);
}
