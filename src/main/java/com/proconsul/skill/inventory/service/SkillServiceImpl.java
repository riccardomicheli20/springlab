package com.proconsul.skill.inventory.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.proconsul.skill.inventory.dto.SkillResponseDto;
import com.proconsul.skill.inventory.mapper.SkillMapper;
import com.proconsul.skill.inventory.repository.SkillRepository;

@Service
public class SkillServiceImpl implements SkillService {
	private final SkillRepository skillRepository;
	private final SkillMapper skillMapper;

	public SkillServiceImpl(SkillRepository skillRepository, SkillMapper skillMapper) {
		this.skillRepository = skillRepository;
		this.skillMapper = skillMapper;
	}

	@Override
	public List<SkillResponseDto> findAllSkills() {
		return skillMapper.toDto(skillRepository.findAll());
               
    }
	}
