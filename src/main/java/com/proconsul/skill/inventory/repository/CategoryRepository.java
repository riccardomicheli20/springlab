package com.proconsul.skill.inventory.repository;

import com.proconsul.skill.inventory.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Boolean existsByName(String name);
	 


}
