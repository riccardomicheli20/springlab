package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.entity.Employee;
import com.proconsul.skill.inventory.exception.EmployeeAlreadyExistException;
import com.proconsul.skill.inventory.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        if (employeeRepository.existsByFiscalCode(employee.getFiscalCode())) {
            throw new EmployeeAlreadyExistException("Employee with fiscal code: " + employee.getFiscalCode() + " " + " already exist");
        } else {
            return employeeRepository.save(employee);
        }
    }
}
