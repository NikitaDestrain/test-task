package com.haulmont.testtask.domain.dto;

import com.haulmont.testtask.domain.auxiliary.Priority;
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
    private Priority priority;
}
