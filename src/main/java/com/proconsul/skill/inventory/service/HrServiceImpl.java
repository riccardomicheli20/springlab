package com.proconsul.skill.inventory.service;

import com.proconsul.skill.inventory.dto.HrResponseDto;
import com.proconsul.skill.inventory.dto.HrUpdateDto;
import com.proconsul.skill.inventory.entity.Hr;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;
import com.proconsul.skill.inventory.mapper.HrMapper;
import com.proconsul.skill.inventory.repository.HrRepository;
import org.springframework.stereotype.Service;

@Service
public class HrServiceImpl implements HrService {

    private final HrRepository hrRepository;
    private final HrMapper hrMapper;

    public HrServiceImpl(HrRepository hrRepository, HrMapper hrMapper) {
        this.hrRepository = hrRepository;
        this.hrMapper = hrMapper;
    }

    @Override
    public HrResponseDto updateHr(HrUpdateDto hrUpdateDto) {
        Hr existHr = hrRepository.findById(hrUpdateDto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("Nessun HR trovato con questa email: " + hrUpdateDto.getEmail()));
        hrMapper.toDto(hrUpdateDto, existHr);
        Hr updatedHr = hrRepository.save(existHr);
        return hrMapper.toHrResponseDto(updatedHr);
    }
}
