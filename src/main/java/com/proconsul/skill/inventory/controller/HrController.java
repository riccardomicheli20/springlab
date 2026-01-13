package com.proconsul.skill.inventory.controller;


import java.util.List;

import com.proconsul.skill.inventory.dto.HrPatchDto;
import com.proconsul.skill.inventory.dto.HrResponseUpdateDto;
import com.proconsul.skill.inventory.dto.HrUpdateDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;
import com.proconsul.skill.inventory.service.HrService;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;

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
	
	@GetMapping("/categories")
	public List<CategoryResponseDto> findAllCategories() {
	    return hrService.findAllCategories();
	}

    @PutMapping
    public HrResponseUpdateDto updateHr(@RequestBody @Valid HrUpdateDto hrUpdateDto){
        return hrService.updateHr(hrUpdateDto);
    }

    @PatchMapping("/email/{email}")
    public HrResponseUpdateDto patchHr(@PathVariable String email, @RequestBody HrPatchDto hrPatchDto) {
        return hrService.patchHr(email,hrPatchDto);
    }

}
