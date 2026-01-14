package com.proconsul.skill.inventory.dto;

import jakarta.validation.constraints.NotBlank;

public class SaveTechnologyDto {

    @NotBlank(message = "inserisci un nome per la tecnologia")
    String technologyName;
    @NotBlank(message = "inserisci il nome di una categoria")
    String categoryName;

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
