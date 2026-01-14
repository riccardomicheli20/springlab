package com.proconsul.skill.inventory.repository;

import com.proconsul.skill.inventory.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {

    Boolean existsByName(String name);


}
