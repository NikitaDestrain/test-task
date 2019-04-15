package com.haulmont.testtask.factory.dto;

import com.haulmont.testtask.domain.auxiliary.Priority;
import com.haulmont.testtask.domain.dto.DoctorDTO;
import com.haulmont.testtask.domain.dto.PatientDTO;
import com.haulmont.testtask.domain.dto.RecipeDTO;

import java.sql.Date;

public class RecipeDTOFactory {
    public static RecipeDTO create(Long id,
                                   String description,
                                   PatientDTO patient,
                                   DoctorDTO doctor,
                                   Date creationDate,
                                   Date expirationDate,
                                   Priority priority) {
        RecipeDTO recipe = new RecipeDTO();
        recipe.setId(id);
        recipe.setDescription(description);
        recipe.setPatient(patient);
        recipe.setDoctor(doctor);
        recipe.setCreationDate(creationDate);
        recipe.setExpirationDate(expirationDate);
        recipe.setPriority(priority);
        return recipe;
    }

    public static RecipeDTO create(String description,
                                   PatientDTO patient,
                                   DoctorDTO doctor,
                                   Date creationDate,
                                   Date expirationDate,
                                   Priority priority) {
        RecipeDTO recipe = new RecipeDTO();
        recipe.setDescription(description);
        recipe.setPatient(patient);
        recipe.setDoctor(doctor);
        recipe.setCreationDate(creationDate);
        recipe.setExpirationDate(expirationDate);
        recipe.setPriority(priority);
        return recipe;
    }
}
