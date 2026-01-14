package com.proconsul.skill.inventory.dto;

public class SavedResponse {
    private Boolean saved;

    public SavedResponse() {
    }

    public SavedResponse(Boolean saved) {
        this.saved = saved;
    }

    public Boolean getSaved() {
        return saved;
    }

    public void setSaved(Boolean saved) {
        this.saved = saved;
    }
}
