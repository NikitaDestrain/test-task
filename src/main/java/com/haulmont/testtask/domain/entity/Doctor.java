package com.haulmont.testtask.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "DOCTOR", schema = "PUBLIC")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "SURNAME")
    private String surname;
    @Basic
    @Column(name = "PATRONYMIC")
    private String patronymic;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SPECIALIZATION_ID", nullable = false)
    private Specialization specialization;
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "doctor",
            targetEntity = Recipe.class)
    private Set<Recipe> recipeSet;
}
