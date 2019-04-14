package com.haulmont.testtask.factory.dto;

import com.haulmont.testtask.domain.dto.DoctorDTO;
import com.haulmont.testtask.domain.dto.SpecializationDTO;

public class DoctorDTOFactory {
    public static DoctorDTO create(Long id, String name, String surname, String patronymic, SpecializationDTO specialization) {
        DoctorDTO doctor = new DoctorDTO();
        doctor.setId(id);
        doctor.setName(name);
        doctor.setSurname(surname);
        doctor.setPatronymic(patronymic);
        doctor.setSpecialization(specialization);
        return doctor;
    }
}
