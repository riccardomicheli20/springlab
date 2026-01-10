package com.proconsul.skill.inventory.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proconsul.skill.inventory.dto.SkillResponseDto;
import com.proconsul.skill.inventory.service.SkillService;

@RestController
@RequestMapping("/rest/api")
public class SkillController {

	  private final SkillService skillService;

	  public SkillController(SkillService skillService) {
			this.skillService = skillService;
	  }

	    @GetMapping("/employees/skills")
	    public List<SkillResponseDto> findAllSkills() {
	        return skillService.findAllSkills();
	    }
	
}
