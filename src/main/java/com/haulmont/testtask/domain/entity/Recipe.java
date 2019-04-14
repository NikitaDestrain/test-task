package com.haulmont.testtask.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "RECIPE", schema = "PUBLIC")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PATIENT_ID", nullable = false)
    private Patient patient;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DOCTOR_ID", nullable = false)
    private Doctor doctor;
    @Basic
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    @Basic
    @Column(name = "EXPIRATION_DATE")
    private Date expirationDate;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRIORITY_ID", nullable = false)
    private Priority priority;
}
