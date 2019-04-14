package com.haulmont.testtask.database.interfaces;

import com.haulmont.testtask.domain.dto.RecipeDTO;
import com.haulmont.testtask.domain.entity.Recipe;
import com.haulmont.testtask.exception.database.DAOEntityCreationException;
import com.haulmont.testtask.exception.database.DAOEntityDeletingException;
import com.haulmont.testtask.exception.database.DAOEntityReadingException;
import com.haulmont.testtask.exception.database.DAOEntityUpdatingException;

import java.util.List;

public interface RecipeDAO {
    public void create(Recipe recipe) throws DAOEntityCreationException;

    public RecipeDTO read(Long id) throws DAOEntityReadingException;

    public void update(Recipe patient) throws DAOEntityUpdatingException;

    public void delete(Recipe patient) throws DAOEntityDeletingException;

    public List<RecipeDTO> getAll() throws DAOEntityReadingException;

    public boolean contains(Long id);
}
