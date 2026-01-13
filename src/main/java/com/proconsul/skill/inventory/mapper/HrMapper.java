package com.proconsul.skill.inventory.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.proconsul.skill.inventory.dto.HrResponseDto;
import com.proconsul.skill.inventory.entity.Hr;
import com.proconsul.skill.inventory.dto.*;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface HrMapper {

	List<HrResponseDto> toHrResponseDtoList(List<Hr> hrs);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	HrResponseUpdateDto toHrResponseDto(Hr hr);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void toDto(HrUpdateDto HrUpdateDto, @MappingTarget Hr Hr);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void patchHrFromDto(HrPatchDto dto, @MappingTarget Hr entity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void patchHrDtoFromEntity(Hr entity, @MappingTarget HrPatchDto dto);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	HrResponseUpdateDto patchToResponseDto(HrPatchDto hrPatchDto);

}
