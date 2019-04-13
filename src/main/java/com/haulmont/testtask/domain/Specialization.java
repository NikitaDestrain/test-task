package com.haulmont.testtask.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "specialization", schema = "PUBLIC")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "specialization")
    private Doctor doctor;
}
