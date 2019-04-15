package com.haulmont.testtask.factory.entity;

import com.haulmont.testtask.domain.entity.Doctor;

public class DoctorFactory {
    public static Doctor create(Long id,
                                String name,
                                String surname,
                                String patronymic,
                                String specialization) {
        Doctor doctor = new Doctor();
        doctor.setId(id);
        doctor.setName(name);
        doctor.setSurname(surname);
        doctor.setPatronymic(patronymic);
        doctor.setSpecialization(specialization);
        return doctor;
    }

    public static Doctor create(String name,
                                String surname,
                                String patronymic,
                                String specialization) {
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setSurname(surname);
        doctor.setPatronymic(patronymic);
        doctor.setSpecialization(specialization);
        return doctor;
    }
}
