package com.haulmont.testtask.domain.dto;

import lombok.Data;

@Data
public class DoctorDTO {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private SpecializationDTO specialization;
}
