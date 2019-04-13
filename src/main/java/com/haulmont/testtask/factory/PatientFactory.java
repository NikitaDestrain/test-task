package com.haulmont.testtask.factory;

import com.haulmont.testtask.domain.Patient;

public class PatientFactory {
    public static Patient create(String name, String surname, String patronymic, String phoneNumber) {
        Patient patient = new Patient();
        patient.setName(name);
        patient.setSurname(surname);
        patient.setPatronymic(patronymic);
        patient.setPhoneNumber(phoneNumber);
        return patient;
    }
}
