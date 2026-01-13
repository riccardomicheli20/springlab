package com.proconsul.skill.inventory.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.proconsul.skill.inventory.dto.HrPatchDto;
import com.proconsul.skill.inventory.dto.HrResponseUpdateDto;
import com.proconsul.skill.inventory.dto.HrUpdateDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.proconsul.skill.inventory.service.HrService;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;
import com.proconsul.skill.inventory.dto.EmployeeResponseDto;
import com.proconsul.skill.inventory.dto.HrResponseDto;

@RestController
@RequestMapping("/rest/api/hrs")
public class HrController {

	private final HrService hrService;

	public HrController(HrService hrService) {

		this.hrService = hrService;
	}

	@GetMapping("/categories")
	public List<CategoryResponseDto> findAllCategories() {
		return hrService.findAllCategories();
	}

	@GetMapping("/hr/employees")
	public ResponseEntity<List<EmployeeResponseDto>> findAllEmployees() {
		return ResponseEntity.ok(hrService.findAllEmployees());
	}

	@GetMapping("/hr")
	public ResponseEntity<List<HrResponseDto>> findAllHr() {
		return ResponseEntity.ok(hrService.findAllHr());
	}

	@PutMapping
	public HrResponseUpdateDto updateHr(@RequestBody @Valid HrUpdateDto hrUpdateDto) {
		return hrService.updateHr(hrUpdateDto);
	}

	@PatchMapping("/email/{email}")
	public HrResponseUpdateDto patchHr(@PathVariable String email, @RequestBody HrPatchDto hrPatchDto) {
		return hrService.patchHr(email, hrPatchDto);
	}

}
