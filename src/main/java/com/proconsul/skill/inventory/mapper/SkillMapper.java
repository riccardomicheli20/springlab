package com.proconsul.skill.inventory.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.proconsul.skill.inventory.dto.SkillResponseDto;
import com.proconsul.skill.inventory.entity.Skill;
import org.springframework.stereotype.Component;

@Component
public class SkillMapper {
	
	public SkillResponseDto toSkillResponseDto(Skill skill) {
		
		if (skill == null) {
			return null;
		}
		
		String techName = null;
		
		if (skill.getTechnology() != null) {
			
			techName = skill.getTechnology().getName();
		}
		
		return new SkillResponseDto(techName, skill.getCategoryName(), skill.getSeniority(), skill.getLevel());
	}
	
	public List<SkillResponseDto> toListSkillResponseDto(List<Skill> skills) {
		
		if (skills == null || skills.isEmpty()) {
			return List.of();
		}
		
		return skills.stream().map(this::toSkillResponseDto).collect(Collectors.toList());
	}
}