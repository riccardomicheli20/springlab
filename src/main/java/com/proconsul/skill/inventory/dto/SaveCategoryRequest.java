package com.proconsul.skill.inventory.dto;

import jakarta.validation.constraints.NotBlank;

public class SaveCategoryRequest {

    @NotBlank(message = "Inserisci il nome della categoria.")
    private String categoryName;

    protected SaveCategoryRequest() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public SaveCategoryRequest(String categoryName) {
        this.categoryName = categoryName;
    }
}
