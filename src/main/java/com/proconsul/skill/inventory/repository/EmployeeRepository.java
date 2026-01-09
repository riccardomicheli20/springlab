package com.proconsul.skill.inventory.repository;

import com.proconsul.skill.inventory.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    public Boolean existsByFiscalCode (String fiscalCode);


}
