package com.proconsul.skill.inventory.service;

import java.util.List;

import com.proconsul.skill.inventory.dto.SkillResponseDto;

public interface SkillService {
	
	public  List<SkillResponseDto> findAllSkills();
}
