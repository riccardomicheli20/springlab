package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.entity.Employee;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

public interface EmployeeService {

    public Employee saveEmployee(Employee employee);
}
