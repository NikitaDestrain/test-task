package com.haulmont.testtask.domain.dto;

import lombok.Data;

@Data
public class PatientDTO {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String phoneNumber;
}
