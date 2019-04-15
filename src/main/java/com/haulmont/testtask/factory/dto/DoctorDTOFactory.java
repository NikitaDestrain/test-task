package com.haulmont.testtask.factory.dto;

import com.haulmont.testtask.domain.dto.DoctorDTO;

public class DoctorDTOFactory {
    public static DoctorDTO create(Long id,
                                   String name,
                                   String surname,
                                   String patronymic,
                                   String specialization) {
        DoctorDTO doctor = new DoctorDTO();
        doctor.setId(id);
        doctor.setName(name);
        doctor.setSurname(surname);
        doctor.setPatronymic(patronymic);
        doctor.setSpecialization(specialization);
        return doctor;
    }

    public static DoctorDTO create(String name,
                                   String surname,
                                   String patronymic,
                                   String specialization) {
        DoctorDTO doctor = new DoctorDTO();
        doctor.setName(name);
        doctor.setSurname(surname);
        doctor.setPatronymic(patronymic);
        doctor.setSpecialization(specialization);
        return doctor;
    }
}