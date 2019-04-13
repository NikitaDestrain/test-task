package com.haulmont.testtask.factory;

import com.haulmont.testtask.domain.Priority;
import com.haulmont.testtask.domain.Recipe;

import java.sql.Date;

public class RecipeFactory {
    public static Recipe create(String description, Date creationDate, Date expirationDate, Priority priority) {
        Recipe recipe = new Recipe();
        recipe.setDescription(description);
        recipe.setCreationDate(creationDate);
        recipe.setExpirationDate(expirationDate);
        recipe.setPriority(priority);
        return recipe;
    }
}
