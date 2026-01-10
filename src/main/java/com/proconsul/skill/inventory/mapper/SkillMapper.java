package com.proconsul.skill.inventory.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.proconsul.skill.inventory.dto.SkillResponseDto;
import com.proconsul.skill.inventory.entity.Skill;

@Mapper(componentModel = "spring")
public interface SkillMapper {
List <SkillResponseDto> toDto(List<Skill> list);
}
