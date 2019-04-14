package com.haulmont.testtask.factory.dto;

import com.haulmont.testtask.domain.dto.PriorityDTO;

public class PriorityDTOFactory {
    public static PriorityDTO create(Long id, String name) {
        PriorityDTO priority = new PriorityDTO();
        priority.setId(id);
        priority.setName(name);
        return priority;
    }
}

