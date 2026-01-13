package com.proconsul.skill.inventory.dto;

public class SaveCategoryResponse {
    private Boolean saved;

    public SaveCategoryResponse() {
    }

    public SaveCategoryResponse(Boolean saved) {
        this.saved = saved;
    }

    public Boolean getSaved() {
        return saved;
    }

    public void setSaved(Boolean saved) {
        this.saved = saved;
    }
}
