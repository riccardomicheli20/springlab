package com.proconsul.skill.inventory.repository;

import com.proconsul.skill.inventory.entity.Hr;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface HrRepository extends JpaRepository<Hr, String> {

	Optional<Hr> findByEmailAndPassword(String email, String password);
		
		
	}



