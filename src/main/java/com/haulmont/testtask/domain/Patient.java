package com.haulmont.testtask.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "patient", schema = "PUBLIC")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "patient")
    private Recipe recipe;
}
