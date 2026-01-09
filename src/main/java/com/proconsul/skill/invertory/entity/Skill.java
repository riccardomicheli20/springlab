package com.proconsul.skill.invertory.entity;

import com.proconsul.skill.invertory.enumerator.Level;
import com.proconsul.skill.invertory.enumerator.Seniority;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Skill {
    //    PK  Long, AutoIncrement Id
//    Class technology    oneToOne
//    Class category
//
//    ENUM seniority (none, junior, middle, senior ) nullable=true
//
//    ENUM level (none,low, medium, high)  nullable=true
//
//    List<Employee> Employees  ManyToMany (Employee)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @OneToOne
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
