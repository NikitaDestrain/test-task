package com.haulmont.testtask.database.utils.dto;

import com.haulmont.testtask.domain.dto.RecipeDTO;
import com.haulmont.testtask.domain.entity.Recipe;
import com.haulmont.testtask.factory.dto.RecipeDTOFactory;

public class RecipeDTOResolver {

    private static volatile RecipeDTOResolver instance;
    private DoctorDTOResolver doctorDTOResolver;
    private PatientDTOResolver patientDTOResolver;
    private PriorityDTOResolver priorityDTOResolver;

    private RecipeDTOResolver() {
        doctorDTOResolver = DoctorDTOResolver.getInstance();
        patientDTOResolver = PatientDTOResolver.getInstance();
        priorityDTOResolver = PriorityDTOResolver.getInstance();
    }

    public static RecipeDTOResolver getInstance() {
        RecipeDTOResolver localInstance = instance;
        if (localInstance == null) {
            synchronized (RecipeDTOResolver.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RecipeDTOResolver();
                }
            }
        }
        return localInstance;
    }

    public RecipeDTO resolve(Recipe recipe) {
        return RecipeDTOFactory.create(
                recipe.getId(),
                recipe.getDescription(),
                patientDTOResolver.resolve(recipe.getPatient()),
                doctorDTOResolver.resolve(recipe.getDoctor()),
                recipe.getCreationDate(),
                recipe.getExpirationDate(),
                priorityDTOResolver.resolve(recipe.getPriority())
        );
    }
}

