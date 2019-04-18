package com.haulmont.testtask.factory.dto;

import com.haulmont.testtask.domain.dto.DoctorStatisticDTO;

public class DoctorStatisticDTOFactory {
    public static DoctorStatisticDTO create(Long doctorId,
                                            String doctorName,
                                            String doctorSurname,
                                            String doctorPatronymic,
                                            int recipeCount) {
        DoctorStatisticDTO doctorStatisticDTO = new DoctorStatisticDTO();
        doctorStatisticDTO.setDoctorId(doctorId);
        doctorStatisticDTO.setDoctorName(doctorName);
        doctorStatisticDTO.setDoctorSurname(doctorSurname);
        doctorStatisticDTO.setDoctorPatronymic(doctorPatronymic);
        doctorStatisticDTO.setRecipeCount(recipeCount);
        return doctorStatisticDTO;
    }
}
