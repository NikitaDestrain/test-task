package com.haulmont.testtask.database.utils.dto;

import com.haulmont.testtask.domain.dto.SpecializationDTO;
import com.haulmont.testtask.domain.entity.Specialization;
import com.haulmont.testtask.factory.dto.SpecializationDTOFactory;

import java.util.HashMap;
import java.util.Map;

public class SpecializationDTOResolver {

    private static volatile SpecializationDTOResolver instance;
    private Map<Long, SpecializationDTO> specializationCache;

    private SpecializationDTOResolver() {
        specializationCache = new HashMap<>();
    }

    public static SpecializationDTOResolver getInstance() {
        SpecializationDTOResolver localInstance = instance;
        if (localInstance == null) {
            synchronized (SpecializationDTOResolver.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SpecializationDTOResolver();
                }
            }
        }
        return localInstance;
    }

    public SpecializationDTO resolve(Specialization specialization) {
        if (specializationCache.get(specialization.getId()) == null) {
            specializationCache.put(specialization.getId(), SpecializationDTOFactory.create(
                    specialization.getId(),
                    specialization.getName()
            ));
        }
        return specializationCache.get(specialization.getId());
    }
}
