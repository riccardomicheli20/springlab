package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.dto.EmployeePatchDto;
import com.proconsul.skill.inventory.dto.EmployeeUpdateDto;
import com.proconsul.skill.inventory.entity.Employee;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;

public interface EmployeeService {

	 /**
     * Aggiorna i dati di un dipendente esistente.
     *
     * <p>
     * Il metodo riceve un {@link EmployeeUpdateDto} contenente il fiscal code
     * del dipendente e i campi da aggiornare.
     * Vengono modificati esclusivamente i campi valorizzati nel DTO.
     * </p>
     *
     * @param employeeUpdateDto DTO contenente il fiscal code del dipendente
     *                          e i dati da aggiornare
     * @return {@link EmployeeUpdateDto} contenente i dati aggiornati
     *
     * @throws ResourceNotFoundException se il dipendente non esiste
     */
    EmployeeUpdateDto updateEmployee(EmployeeUpdateDto employeePUpdateDto);
	
	public EmployeePatchDto patchEmployee(String fiscalCode, EmployeePatchDto employeePatchDto);	

    public Employee saveEmployee(Employee employee);

}

