package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.dto.HrResponseDto;
import com.proconsul.skill.inventory.dto.HrUpdateDto;

public interface HrService {

    HrResponseDto updateHr(HrUpdateDto hrUpdateDto);


}
