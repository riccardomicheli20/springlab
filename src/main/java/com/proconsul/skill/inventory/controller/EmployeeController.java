package com.proconsul.skill.inventory.controller;

import com.proconsul.skill.inventory.dto.*;
import com.proconsul.skill.inventory.exception.AccessNotValidException;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;
import com.proconsul.skill.inventory.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.*;

import com.proconsul.skill.inventory.entity.Employee;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
	public EmployeeResponseDto saveEmployee(@RequestBody @Valid Employee employee) {
		return employeeService.saveEmployee(employee);
	}
	
	@GetMapping("/skills")
	public List<SkillResponseDto> findAllSkills() {
		return employeeService.findAllSkills();
	}
	
	@PostMapping("/auth/login")
	public LoginEmployeeDto loginEmployee(@RequestBody @Valid LoginEmployeeRequest request) throws AccessNotValidException, ResourceNotFoundException {
		return employeeService.loginEmployee(request);
	}
	
	@GetMapping("/fiscalcode/{fiscalCode}")
	public EmployeeResponseDto findEmployeeByFiscalCode(@PathVariable ("fiscalCode") String fiscalCode) throws ResourceNotFoundException {
		return employeeService.findByFiscalCode(fiscalCode);
	}
	
}
