package com.proconsul.skill.inventory.controller;

import com.proconsul.skill.inventory.dto.EmployeePatchDto;
import com.proconsul.skill.inventory.service.EmployeeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/employees")
public class EmployeeController {
	
	public EmployeeServiceImpl employeeServiceImpl;
	
	public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
		this.employeeServiceImpl = employeeServiceImpl;
	}
	
	@PatchMapping("/fiscalCode/{fiscalCode}")
	public ResponseEntity<EmployeePatchDto> partialUpdateEmployee(@PathVariable String fiscalCode, @RequestBody EmployeePatchDto employeePatchDto) {
		
		EmployeePatchDto updatedDto = employeeServiceImpl.patchEmployee(fiscalCode, employeePatchDto);
		
		return ResponseEntity.ok(updatedDto);
	}
	
}
