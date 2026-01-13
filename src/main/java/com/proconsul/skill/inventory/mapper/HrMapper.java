package com.proconsul.skill.inventory.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.proconsul.skill.inventory.dto.HrResponseDto;
import com.proconsul.skill.inventory.entity.Hr;

@Mapper(componentModel = "spring")
public interface HrMapper {

	
	HrResponseDto toHrResponseDto(Hr hr);

    List<HrResponseDto> toHrResponseDtoList(List<Hr> hrs);

}
