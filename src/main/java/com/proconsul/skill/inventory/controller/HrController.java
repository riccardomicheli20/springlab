package com.proconsul.skill.inventory.controller;


import com.proconsul.skill.inventory.exception.ResourceNotFoundException;
import com.proconsul.skill.inventory.service.EmployeeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rest/api/hrs")
public class HrController {
	
	private final EmployeeService employeeService;
	
	public HrController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@DeleteMapping("/fiscalcode/{fiscalCode}")
	public Map<String, Boolean> deleteEmployee(@PathVariable String fiscalCode) throws ResourceNotFoundException {
		return employeeService.deleteEmployeeByFiscalCode(fiscalCode);
	}

}
