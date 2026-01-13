package com.proconsul.skill.inventory.mapper;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.proconsul.skill.inventory.dto.CategoryResponseDto;
import com.proconsul.skill.inventory.entity.Category;

@Component
public class CategoryMapper {

	public CategoryResponseDto toCategoryResponseDto(Category category) {

		if (category == null) {
			return null;
		}

		String technologyName = null;

		if (category.getTechnologies() != null && !category.getTechnologies().isEmpty()) {
			// prendo una technology (es. la prima)
			technologyName = category.getTechnologies().get(0).getName();
		}

		return new CategoryResponseDto(category.getName(), technologyName);
	}

	public List<CategoryResponseDto> toListCategoryResponseDto(List<Category> categories) {

		if (categories == null || categories.isEmpty()) {
			return List.of();
		}

		return categories.stream().map(this::toCategoryResponseDto).collect(Collectors.toList());
	}
}
