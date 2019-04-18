package com.haulmont.testtask.domain.dto;

import lombok.Data;

@Data
public class DoctorStatisticDTO {
    private Long doctorId;
    private String doctorName;
    private String doctorSurname;
    private String doctorPatronymic;
    private int recipeCount;
}
