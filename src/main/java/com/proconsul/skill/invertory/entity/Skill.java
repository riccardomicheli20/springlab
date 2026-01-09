package com.proconsul.skill.invertory.entity;

import java.util.List;

import com.proconsul.skill.invertory.enumerator.Level;
import com.proconsul.skill.invertory.enumerator.Seniority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional=false)
    private Technology technology;

    @NotBlank
    @Column(name = "category_name")
    private String categoryName;


    @Enumerated(EnumType.STRING)
    private Seniority seniority;

    @Enumerated(EnumType.STRING)
    private Level level;

    @ManyToMany(mappedBy = "skills", fetch = FetchType.EAGER)
    private List<Employee> employees;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Seniority getSeniority() {
        return seniority;
    }

    public void setSeniority(Seniority seniority) {
        this.seniority = seniority;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    protected Skill() {
    }

    public Skill(Technology technology, String categoryName, Seniority seniority, Level level, List<Employee> employees) {
        this.technology = technology;
        this.categoryName = categoryName;
        this.seniority = seniority;
        this.level = level;
        this.employees = employees;
    }
}
