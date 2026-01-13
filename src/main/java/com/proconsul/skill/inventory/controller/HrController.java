package com.proconsul.skill.inventory.controller;


import java.util.List;

import com.proconsul.skill.inventory.dto.HrResponseDto;
import com.proconsul.skill.inventory.dto.HrUpdateDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping()
    public  HrResponseDto updateHr(@RequestBody @Valid HrUpdateDto hrUpdateDto){
        return hrService.updateHr(hrUpdateDto);
    }

}
