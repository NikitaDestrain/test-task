package com.haulmont.testtask.factory;

import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.domain.Specialization;

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
