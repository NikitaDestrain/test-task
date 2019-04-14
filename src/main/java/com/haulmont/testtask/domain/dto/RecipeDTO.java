package com.haulmont.testtask.domain.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class RecipeDTO {
    private Long id;
    private String description;
    private PatientDTO patient;
    private DoctorDTO doctor;
    private Date creationDate;
    private Date expirationDate;
    private PriorityDTO priority;
}
