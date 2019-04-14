package com.haulmont.testtask.database.utils.dto;

import com.haulmont.testtask.domain.dto.PatientDTO;
import com.haulmont.testtask.domain.entity.Patient;
import com.haulmont.testtask.factory.dto.PatientDTOFactory;

public class PatientDTOResolver {

    private static volatile PatientDTOResolver instance;

    public static PatientDTOResolver getInstance() {
        PatientDTOResolver localInstance = instance;
        if (localInstance == null) {
            synchronized (PatientDTOResolver.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PatientDTOResolver();
                }
            }
        }
        return localInstance;
    }

    public PatientDTO resolve(Patient patient) {
        return PatientDTOFactory.create(
                patient.getId(),
                patient.getName(),
                patient.getSurname(),
                patient.getPatronymic(),
                patient.getPhoneNumber()
        );
    }
}
