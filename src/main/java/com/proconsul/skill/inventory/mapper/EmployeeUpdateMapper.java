package com.proconsul.skill.inventory.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.proconsul.skill.inventory.dto.EmployeeUpdateDto;
import com.proconsul.skill.inventory.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeUpdateMapper {

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "skills", ignore = true)
	void toDto(EmployeeUpdateDto employeeUpdateDto, @MappingTarget Employee employee);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void toEntity(Employee employee, @MappingTarget EmployeeUpdateDto employeePatchDto);
}
