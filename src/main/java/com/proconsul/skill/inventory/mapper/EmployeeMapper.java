package com.proconsul.skill.inventory.mapper;

import com.proconsul.skill.inventory.dto.EmployeePatchDto;
import com.proconsul.skill.inventory.dto.EmployeeResponseDto;
import com.proconsul.skill.inventory.dto.EmployeeUpdateDto;
import com.proconsul.skill.inventory.entity.Employee;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void patchEmployeeFromDto(EmployeePatchDto dto, @MappingTarget Employee entity);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void patchEmployeeDtoFromEntity(Employee entity, @MappingTarget EmployeePatchDto dto);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void toDto(EmployeeUpdateDto employeeUpdateDto, @MappingTarget Employee employee);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void toEntity(Employee employee, @MappingTarget EmployeeUpdateDto employeePatchDto);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	EmployeeResponseDto toEmployeeResponseDto(Employee employee);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	EmployeeResponseDto patchToResponseDto (EmployeePatchDto employeePatchDto);
	
}
