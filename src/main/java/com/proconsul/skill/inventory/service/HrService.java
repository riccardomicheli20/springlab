package com.proconsul.skill.inventory.service;

import java.util.Map;

import com.proconsul.skill.inventory.dto.HrResponseDto;

public interface HrService {

	Map<String, Boolean> deleteHr(String email);

	HrResponseDto findHrByEmail(String email);

}
