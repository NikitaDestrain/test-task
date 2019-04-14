package com.haulmont.testtask.database.utils.dto;

import com.haulmont.testtask.domain.dto.DoctorDTO;
import com.haulmont.testtask.domain.entity.Doctor;
import com.haulmont.testtask.factory.dto.DoctorDTOFactory;

public class DoctorDTOResolver {

    private static volatile DoctorDTOResolver instance;
    private SpecializationDTOResolver specializationDTOResolver;

    private DoctorDTOResolver() {
        specializationDTOResolver = specializationDTOResolver.getInstance();
    }

    public static DoctorDTOResolver getInstance() {
        DoctorDTOResolver localInstance = instance;
        if (localInstance == null) {
            synchronized (DoctorDTOResolver.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DoctorDTOResolver();
                }
            }
        }
        return localInstance;
    }

    public DoctorDTO resolve(Doctor doctor) {
        return DoctorDTOFactory.create(
                doctor.getId(),
                doctor.getName(),
                doctor.getSurname(),
                doctor.getPatronymic(),
                specializationDTOResolver.resolve(doctor.getSpecialization())
        );
    }
}
