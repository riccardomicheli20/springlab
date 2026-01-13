package com.proconsul.skill.inventory.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.proconsul.skill.inventory.dto.HrResponseDto;
import com.proconsul.skill.inventory.entity.Hr;
import com.proconsul.skill.inventory.exception.ResourceNotFoundException;
import com.proconsul.skill.inventory.mapper.HrMapper;
import com.proconsul.skill.inventory.repository.HrRepository;

@Service
public class HrServiceImpl implements HrService {

	private final HrRepository hrRepository;
	private final HrMapper hrMapper;

	public HrServiceImpl(HrRepository hrRepository, HrMapper hrMapper) {
		this.hrRepository = hrRepository;
		this.hrMapper = hrMapper;
	}

	@Override
	public Map<String, Boolean> deleteHr(String email) {

		Hr hr = hrRepository.findById(email)
				.orElseThrow(() -> new ResourceNotFoundException("HR not found with email: " + email));

		hrRepository.delete(hr);

		return Map.of("deleted", true);
	}

	@Override
	public HrResponseDto findHrByEmail(String email) {

		Hr hr = hrRepository.findById(email)
				.orElseThrow(() -> new ResourceNotFoundException("HR not found with email: " + email));

		return hrMapper.toHrResponseDto(hr);

	}

}
