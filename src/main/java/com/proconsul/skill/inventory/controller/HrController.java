package com.proconsul.skill.inventory.controller;

import java.util.List;
import java.util.Map;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;
import com.proconsul.skill.inventory.dto.HrPatchDto;
import com.proconsul.skill.inventory.dto.HrRequestDto.HrLoginRequestDto;
import com.proconsul.skill.inventory.dto.HrResponseDto;
import com.proconsul.skill.inventory.dto.HrResponseUpdateDto;
import com.proconsul.skill.inventory.dto.HrUpdateDto;
import com.proconsul.skill.inventory.dto.SaveCategoryRequest;
import com.proconsul.skill.inventory.dto.SaveCategoryResponse;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;
import com.proconsul.skill.inventory.service.HrService;

import jakarta.validation.Valid;
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

	@DeleteMapping("/fiscalcode/{fiscalCode}")
	public Map<String, Boolean> deleteEmployee(@PathVariable String fiscalCode) throws ResourceNotFoundException {
		return hrService.deleteEmployeeByFiscalCode(fiscalCode);
	}

	@GetMapping("/categories")
	public List<CategoryResponseDto> findAllCategories() {
		return hrService.findAllCategories();
	}

	@PostMapping("/category")
	public SaveCategoryResponse saveCategory(@RequestBody @Valid SaveCategoryRequest request) {

		return hrService.saveCategory(request);
	}

	@PutMapping
	public HrResponseUpdateDto updateHr(@RequestBody @Valid HrUpdateDto hrUpdateDto) {
		return hrService.updateHr(hrUpdateDto);
	}

	@PatchMapping("/email/{email}")
	public HrResponseUpdateDto patchHr(@PathVariable String email, @RequestBody HrPatchDto hrPatchDto) {
		return hrService.patchHr(email, hrPatchDto);
	}

	@PostMapping("/login")
	public HrResponseDto loginHr(@RequestBody @Valid HrLoginRequestDto request) {
		return hrService.login(request);
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
