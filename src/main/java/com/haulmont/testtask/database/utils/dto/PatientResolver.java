package com.haulmont.testtask.database.utils.dto;

import com.haulmont.testtask.domain.dto.PatientDTO;
import com.haulmont.testtask.domain.entity.Patient;
import com.haulmont.testtask.factory.dto.PatientDTOFactory;
import com.haulmont.testtask.factory.entity.PatientFactory;

public class PatientResolver {

    private static volatile PatientResolver instance;

    private PatientResolver() {
    }

    public static PatientResolver getInstance() {
        PatientResolver localInstance = instance;
        if (localInstance == null) {
            synchronized (PatientResolver.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PatientResolver();
                }
            }
        }
        return localInstance;
    }

    public PatientDTO resolveToDTO(Patient patient) {
        return PatientDTOFactory.create(
                patient.getId(),
                patient.getName(),
                patient.getSurname(),
                patient.getPatronymic(),
                patient.getPhoneNumber()
        );
    }

    public Patient resolveToEntity(PatientDTO patient) {
        return PatientFactory.create(
                patient.getId(),
                patient.getName(),
                patient.getSurname(),
                patient.getPatronymic(),
                patient.getPhoneNumber()
        );
    }
}
