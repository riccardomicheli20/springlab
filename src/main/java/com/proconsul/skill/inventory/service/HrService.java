package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface HrService {
	
	Map<String, Boolean> deleteEmployeeByFiscalCode(String fiscalCode) throws ResourceNotFoundException;

}
