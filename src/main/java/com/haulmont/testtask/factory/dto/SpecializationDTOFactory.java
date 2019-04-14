package com.haulmont.testtask.factory.dto;

import com.haulmont.testtask.domain.dto.SpecializationDTO;

public class SpecializationDTOFactory {
    public static SpecializationDTO create(Long id, String name) {
        SpecializationDTO specialization = new SpecializationDTO();
        specialization.setId(id);
        specialization.setName(name);
        return specialization;
    }
}
