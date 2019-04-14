package com.haulmont.testtask.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "PATIENT", schema = "PUBLIC")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "PATRONYMIC")
    private String patronymic;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "patient",
            targetEntity=Recipe.class)
    private Set<Recipe> recipeSet;
}
