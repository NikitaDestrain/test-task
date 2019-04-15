package com.haulmont.testtask.factory.dto;

import com.haulmont.testtask.domain.dto.PatientDTO;

public class PatientDTOFactory {
    public static PatientDTO create(Long id,
                                    String name,
                                    String surname,
                                    String patronymic,
                                    String phoneNumber) {
        PatientDTO patient = new PatientDTO();
        patient.setId(id);
        patient.setName(name);
        patient.setSurname(surname);
        patient.setPatronymic(patronymic);
        patient.setPhoneNumber(phoneNumber);
        return patient;
    }

    public static PatientDTO create(String name,
                                    String surname,
                                    String patronymic,
                                    String phoneNumber) {
        PatientDTO patient = new PatientDTO();
        patient.setName(name);
        patient.setSurname(surname);
        patient.setPatronymic(patronymic);
        patient.setPhoneNumber(phoneNumber);
        return patient;
    }
}
