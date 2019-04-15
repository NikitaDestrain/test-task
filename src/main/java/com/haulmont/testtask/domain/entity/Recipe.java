package com.haulmont.testtask.domain.entity;

import com.haulmont.testtask.database.utils.hibernate.PriorityConverter;
import com.haulmont.testtask.domain.auxiliary.Priority;
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
    @Column(name = "PRIORITY")
    @Convert(converter = PriorityConverter.class)
    private Priority priority;
}
