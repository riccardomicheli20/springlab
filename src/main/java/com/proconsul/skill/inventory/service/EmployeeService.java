package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.dto.EmployeeUpdateDto;

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



}

