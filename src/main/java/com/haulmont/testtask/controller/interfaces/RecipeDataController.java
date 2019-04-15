package com.haulmont.testtask.controller.interfaces;

import com.haulmont.testtask.domain.dto.RecipeDTO;
import com.haulmont.testtask.exception.controller.DataControllerCreationException;
import com.haulmont.testtask.exception.controller.DataControllerReadingException;
import com.haulmont.testtask.exception.controller.DataControllerUpdatingException;

import java.util.List;

public interface RecipeDataController {
    void create(RecipeDTO recipe) throws DataControllerCreationException;

    RecipeDTO get(Long id) throws DataControllerReadingException;

    void update(RecipeDTO recipe) throws DataControllerUpdatingException;

    List<RecipeDTO> getAll() throws DataControllerReadingException;

    List<RecipeDTO> getFilteredByRegex(String regex, String parameter) throws DataControllerReadingException;
}
