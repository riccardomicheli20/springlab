package com.proconsul.skill.inventory.repository;

import com.proconsul.skill.inventory.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
	
	Optional<Employee> findByFiscalCode(String fiscalCode);
	

    public Boolean existsByFiscalCode (String fiscalCode);


}
