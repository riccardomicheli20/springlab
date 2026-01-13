package com.proconsul.skill.inventory.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;
import com.proconsul.skill.inventory.service.HrService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;
import com.proconsul.skill.inventory.service.HrService;

import java.util.Map;

@RestController
@RequestMapping("/rest/api/hrs")
public class HrController {
	
	private final HrService hrService;
	
	public HrController(HrService hrService) {
		this.hrService = hrService;
	}
	
	@DeleteMapping("/fiscalcode/{fiscalCode}")
	public Map<String, Boolean> deleteEmployee(@PathVariable String fiscalCode) throws ResourceNotFoundException {
		return hrService.deleteEmployeeByFiscalCode(fiscalCode);
	}

	private HrService hrService;
	public HrController(HrService hrService) {
		
		this.hrService = hrService;
	}

	@GetMapping("/categories")
	public List<CategoryResponseDto> findAllCategories() {
	    return hrService.findAllCategories();
	}
	
}
