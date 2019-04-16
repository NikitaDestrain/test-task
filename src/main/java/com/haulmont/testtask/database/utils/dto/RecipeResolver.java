package com.haulmont.testtask.database.utils.dto;

import com.haulmont.testtask.domain.dto.RecipeDTO;
import com.haulmont.testtask.domain.entity.Recipe;
import com.haulmont.testtask.factory.dto.RecipeDTOFactory;
import com.haulmont.testtask.factory.entity.RecipeFactory;

public class RecipeResolver {

    private static volatile RecipeResolver instance;
    private DoctorResolver doctorResolver;
    private PatientResolver patientResolver;

    private RecipeResolver() {
        doctorResolver = DoctorResolver.getInstance();
        patientResolver = PatientResolver.getInstance();
    }

    public static RecipeResolver getInstance() {
        RecipeResolver localInstance = instance;
        if (localInstance == null) {
            synchronized (RecipeResolver.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RecipeResolver();
                }
            }
        }
        return localInstance;
    }

    public RecipeDTO resolveToDTO(Recipe recipe) {
        return RecipeDTOFactory.create(
                recipe.getId(),
                recipe.getDescription(),
                patientResolver.resolveToDTO(recipe.getPatient()),
                doctorResolver.resolveToDTO(recipe.getDoctor()),
                recipe.getCreationDate(),
                recipe.getExpirationDate(),
                recipe.getPriority()
        );
    }

    public Recipe resolveToEntity(RecipeDTO recipe) {
        return RecipeFactory.create(
                recipe.getId(),
                recipe.getDescription(),
                patientResolver.resolveToEntity(recipe.getPatient()),
                doctorResolver.resolveToEntity(recipe.getDoctor(), null),
                recipe.getCreationDate(),
                recipe.getExpirationDate(),
                recipe.getPriority()
        );
    }
}

