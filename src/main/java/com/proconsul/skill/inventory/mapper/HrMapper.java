package com.proconsul.skill.inventory.mapper;

import com.proconsul.skill.inventory.dto.*;
import com.proconsul.skill.inventory.entity.Employee;
import com.proconsul.skill.inventory.dto.HrResponseDto;
import com.proconsul.skill.inventory.entity.Hr;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface HrMapper {
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	HrResponseUpdateDto toHrResponseUpdateDto(Hr hr);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void toDto(HrUpdateDto HrUpdateDto, @MappingTarget Hr Hr);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void patchHrFromDto(HrPatchDto dto, @MappingTarget Hr entity);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void patchHrDtoFromEntity(Hr entity, @MappingTarget HrPatchDto dto);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	HrResponseUpdateDto patchToResponseDto (HrPatchDto hrPatchDto);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	HrResponseDto toHrResponseDto(Hr hr);
}
