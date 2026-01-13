package com.proconsul.skill.inventory.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;
import com.proconsul.skill.inventory.service.HrService;

@RestController
@RequestMapping("/rest/api/hrs")
public class HrController {

	private HrService hrService;
	public HrController(HrService hrService) {
		
		this.hrService = hrService;
	}

	@GetMapping("/categories")
	public List<CategoryResponseDto> findAllCategories() {
	    return hrService.findAllCategories();
	}
	
}
