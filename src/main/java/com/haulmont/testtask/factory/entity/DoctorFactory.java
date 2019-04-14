package com.haulmont.testtask.factory.entity;

import com.haulmont.testtask.domain.entity.Doctor;
import com.haulmont.testtask.domain.entity.Specialization;

public class DoctorFactory {
    public static Doctor create(String name, String surname, String patronymic, Specialization specialization) {
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setSurname(surname);
        doctor.setPatronymic(patronymic);
        doctor.setSpecialization(specialization);
        return doctor;
    }
}
