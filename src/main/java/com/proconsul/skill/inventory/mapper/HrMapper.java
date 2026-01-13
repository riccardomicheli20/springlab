package com.proconsul.skill.inventory.mapper;

import com.proconsul.skill.inventory.dto.EmployeeResponseDto;
import com.proconsul.skill.inventory.dto.EmployeeUpdateDto;
import com.proconsul.skill.inventory.dto.HrResponseDto;
import com.proconsul.skill.inventory.dto.HrUpdateDto;
import com.proconsul.skill.inventory.entity.Employee;
import com.proconsul.skill.inventory.entity.Hr;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface HrMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    HrResponseDto toHrResponseDto(Hr hr);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toDto(HrUpdateDto HrUpdateDto, @MappingTarget Hr Hr);
}
