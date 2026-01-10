package com.proconsul.skill.inventory.controller;

import com.proconsul.skill.inventory.dto.EmployeeUpdateDto;
import com.proconsul.skill.inventory.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import com.proconsul.skill.inventory.dto.EmployeePatchDto;
import org.springframework.web.bind.annotation.*;

import com.proconsul.skill.inventory.entity.Employee;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/employees")
public class EmployeeController {


	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PutMapping
	public ResponseEntity<EmployeeUpdateDto> updateEmployee(@RequestBody @Valid EmployeeUpdateDto dto) {

		return ResponseEntity.ok(employeeService.updateEmployee(dto));
	}		
	
	@PatchMapping("/fiscalCode/{fiscalCode}")
	public ResponseEntity<EmployeePatchDto> partialUpdateEmployee(@PathVariable String fiscalCode, @RequestBody EmployeePatchDto employeePatchDto) {
		
		EmployeePatchDto updatedDto = employeeService.patchEmployee(fiscalCode, employeePatchDto);
		
		return ResponseEntity.ok(updatedDto);
	}
	
    @PostMapping
    public Employee saveEmployee(@RequestBody @Valid Employee employee) {
        return employeeService.saveEmployee(employee);
    }

}
