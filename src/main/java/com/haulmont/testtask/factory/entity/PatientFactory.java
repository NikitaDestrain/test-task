package com.haulmont.testtask.factory.entity;

import com.haulmont.testtask.domain.entity.Patient;

public class PatientFactory {
    public static Patient create(Long id,
                                 String name,
                                 String surname,
                                 String patronymic,
                                 String phoneNumber) {
        Patient patient = new Patient();
        patient.setId(id);
        patient.setName(name);
        patient.setSurname(surname);
        patient.setPatronymic(patronymic);
        patient.setPhoneNumber(phoneNumber);
        return patient;
    }

    public static Patient create(String name,
                                 String surname,
                                 String patronymic,
                                 String phoneNumber) {
        Patient patient = new Patient();
        patient.setName(name);
        patient.setSurname(surname);
        patient.setPatronymic(patronymic);
        patient.setPhoneNumber(phoneNumber);
        return patient;
    }
}
