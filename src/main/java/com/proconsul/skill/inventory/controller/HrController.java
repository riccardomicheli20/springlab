package com.proconsul.skill.inventory.controller;


import java.util.Map;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proconsul.skill.inventory.dto.HrResponseDto;
import com.proconsul.skill.inventory.service.HrService;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/rest/api/hr")
@Validated
public class HrController {
	
	private final HrService hrService;
		
	public HrController(HrService hrService) {
		this.hrService = hrService;
	}

	@DeleteMapping("/email/{email}")
	public Map<String, Boolean> deleteHr(@PathVariable @NotBlank @Email String email) {
	    return hrService.deleteHr(email);
	}
	
	@GetMapping("/email/{email}")
    public HrResponseDto findHrByEmail(@PathVariable @NotBlank @Email String email) {
        return hrService.findHrByEmail(email);
    }


}
