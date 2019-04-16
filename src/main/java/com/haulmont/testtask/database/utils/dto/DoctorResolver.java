package com.haulmont.testtask.database.utils.dto;

import com.haulmont.testtask.domain.dto.DoctorDTO;
import com.haulmont.testtask.domain.entity.Doctor;
import com.haulmont.testtask.factory.dto.DoctorDTOFactory;
import com.haulmont.testtask.factory.entity.DoctorFactory;

public class DoctorResolver {

    private static volatile DoctorResolver instance;

    private DoctorResolver() {
    }

    public static DoctorResolver getInstance() {
        DoctorResolver localInstance = instance;
        if (localInstance == null) {
            synchronized (DoctorResolver.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DoctorResolver();
                }
            }
        }
        return localInstance;
    }

    public DoctorDTO resolveToDTO(Doctor doctor) {
        return DoctorDTOFactory.create(
                doctor.getId(),
                doctor.getName(),
                doctor.getSurname(),
                doctor.getPatronymic(),
                doctor.getSpecialization()
        );
    }

    public Doctor resolveToEntity(DoctorDTO doctor, Doctor currentDoctor) {
        if (currentDoctor != null) {
            currentDoctor.setName(doctor.getName());
            currentDoctor.setSurname(doctor.getSurname());
            currentDoctor.setPatronymic(doctor.getPatronymic());
            currentDoctor.setSpecialization(doctor.getSpecialization());
            return currentDoctor;
        } else {
            return DoctorFactory.create(
                    doctor.getId(),
                    doctor.getName(),
                    doctor.getSurname(),
                    doctor.getPatronymic(),
                    doctor.getSpecialization()
            );
        }
    }
}
