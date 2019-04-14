package com.haulmont.testtask.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "PRIORITY", schema = "PUBLIC")
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "priority",
            targetEntity=Recipe.class)
    private Set<Recipe> recipeSet;
}
