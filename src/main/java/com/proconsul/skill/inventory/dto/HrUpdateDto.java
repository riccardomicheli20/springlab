package com.proconsul.skill.inventory.dto;

import com.proconsul.skill.inventory.enumerator.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class HrUpdateDto {

    @Email
    private String email;

    @NotBlank(message = "il campo è obbligatorio")
    private String firstName;

    @NotBlank(message = "il campo è obbligatorio")
    private String lastName;

    @NotBlank(message = "il campo è obbligatorio")
    @Enumerated(EnumType.STRING)
    private Role role;

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public @NotBlank(message = "il campo è obbligatorio") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "il campo è obbligatorio") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "il campo è obbligatorio") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "il campo è obbligatorio") String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank(message = "il campo è obbligatorio") Role getRole() {
        return role;
    }

    public void setRole(@NotBlank(message = "il campo è obbligatorio") Role role) {
        this.role = role;
    }

    public HrUpdateDto(String email, String firstName, String lastName, Role role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    protected HrUpdateDto() {
    }
}