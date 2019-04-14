package com.haulmont.testtask.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "SPECIALIZATION", schema = "PUBLIC")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "specialization",
            targetEntity = Doctor.class)
    private Set<Doctor> doctorSet;
}
