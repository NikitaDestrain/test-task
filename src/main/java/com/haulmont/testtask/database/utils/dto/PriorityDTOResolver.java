package com.haulmont.testtask.database.utils.dto;

import com.haulmont.testtask.domain.dto.PriorityDTO;
import com.haulmont.testtask.domain.entity.Priority;
import com.haulmont.testtask.factory.dto.PriorityDTOFactory;

import java.util.HashMap;
import java.util.Map;

public class PriorityDTOResolver {

    private static volatile PriorityDTOResolver instance;
    private Map<Long, PriorityDTO> priorityCache;

    private PriorityDTOResolver() {
        priorityCache = new HashMap<>();
    }

    public static PriorityDTOResolver getInstance() {
        PriorityDTOResolver localInstance = instance;
        if (localInstance == null) {
            synchronized (PriorityDTOResolver.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PriorityDTOResolver();
                }
            }
        }
        return localInstance;
    }

    public PriorityDTO resolve(Priority priority) {
        if (priorityCache.get(priority.getId()) == null) {
            priorityCache.put(priority.getId(), PriorityDTOFactory.create(
                    priority.getId(),
                    priority.getName()
            ));
        }
        return priorityCache.get(priority.getId());
    }
}
