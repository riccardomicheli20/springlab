package com.proconsul.skill.inventory.controller;

import com.proconsul.skill.inventory.dto.EmployeePatchDto;
import com.proconsul.skill.inventory.service.EmployeeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proconsul.skill.inventory.entity.Employee;
import com.proconsul.skill.inventory.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/employees")
public class EmployeeController {
	
	
	
	@PatchMapping("/fiscalCode/{fiscalCode}")
	public ResponseEntity<EmployeePatchDto> partialUpdateEmployee(@PathVariable String fiscalCode, @RequestBody EmployeePatchDto employeePatchDto) {
		
		EmployeePatchDto updatedDto = employeeService.patchEmployee(fiscalCode, employeePatchDto);
		
		return ResponseEntity.ok(updatedDto);
	}
	

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody @Valid Employee employee) {
        return employeeService.saveEmployee(employee);
    }

}
