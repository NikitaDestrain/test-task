package com.haulmont.testtask.factory.entity;

import com.haulmont.testtask.domain.entity.Doctor;
import com.haulmont.testtask.domain.entity.Patient;
import com.haulmont.testtask.domain.entity.Priority;
import com.haulmont.testtask.domain.entity.Recipe;

import java.sql.Date;

public class RecipeFactory {
    public static Recipe create(Long id, String description, Patient patient, Doctor doctor, Date creationDate, Date expirationDate, Priority priority) {
        Recipe recipe = new Recipe();
        recipe.setId(id);
        recipe.setDescription(description);
        recipe.setPatient(patient);
        recipe.setDoctor(doctor);
        recipe.setCreationDate(creationDate);
        recipe.setExpirationDate(expirationDate);
        recipe.setPriority(priority);
        return recipe;
    }
}
