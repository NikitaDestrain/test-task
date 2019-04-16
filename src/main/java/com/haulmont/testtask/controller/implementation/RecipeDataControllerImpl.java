package com.haulmont.testtask.controller.implementation;

import com.haulmont.testtask.controller.interfaces.RecipeDataController;
import com.haulmont.testtask.database.interfaces.RecipeDAO;
import com.haulmont.testtask.domain.dto.RecipeDTO;
import com.haulmont.testtask.exception.controller.DataControllerCreationException;
import com.haulmont.testtask.exception.controller.DataControllerReadingException;
import com.haulmont.testtask.exception.controller.DataControllerRemovingException;
import com.haulmont.testtask.exception.controller.DataControllerUpdatingException;
import com.haulmont.testtask.exception.database.DAOEntityCreationException;
import com.haulmont.testtask.exception.database.DAOEntityDeletingException;
import com.haulmont.testtask.exception.database.DAOEntityReadingException;
import com.haulmont.testtask.exception.database.DAOEntityUpdatingException;

import java.util.List;

public class RecipeDataControllerImpl implements RecipeDataController {

    private RecipeDAO recipeDAO;

    public RecipeDataControllerImpl(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    @Override
    public void create(RecipeDTO recipe) throws DataControllerCreationException {
        try {
            recipeDAO.create(recipe);
        } catch (DAOEntityCreationException e) {
            throw new DataControllerCreationException(e.getMessage());
        }
    }

    @Override
    public RecipeDTO get(Long id) throws DataControllerReadingException {
        try {
            return recipeDAO.read(id);
        } catch (DAOEntityReadingException e) {
            throw new DataControllerReadingException(e.getMessage());
        }
    }

    @Override
    public void update(RecipeDTO recipe) throws DataControllerUpdatingException {
        try {
            recipeDAO.update(recipe);
        } catch (DAOEntityUpdatingException e) {
            throw new DataControllerUpdatingException(e.getMessage());
        }
    }

    @Override
    public void remove(Long id) throws DataControllerRemovingException {
        try {
            RecipeDTO recipe = recipeDAO.read(id);
            recipeDAO.delete(recipe);
        } catch (DAOEntityDeletingException | DAOEntityReadingException e) {
            throw new DataControllerRemovingException(e.getMessage());
        }
    }

    @Override
    public List<RecipeDTO> getAll() throws DataControllerReadingException {
        try {
            return recipeDAO.getAll();
        } catch (DAOEntityReadingException e) {
            throw new DataControllerReadingException(e.getMessage());
        }
    }
}