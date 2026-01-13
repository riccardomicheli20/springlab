package com.proconsul.skill.inventory.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.proconsul.skill.inventory.dto.HrResponseDto;
import com.proconsul.skill.inventory.entity.Hr;

@Mapper(componentModel = "spring")
public interface HrMapper {

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	HrResponseDto toHrResponseDto(Hr hr);

}
