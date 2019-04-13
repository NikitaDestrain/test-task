package com.haulmont.testtask.database.interfaces;

import com.haulmont.testtask.domain.Recipe;
import com.haulmont.testtask.exception.database.DAOEntityCreationException;
import com.haulmont.testtask.exception.database.DAOEntityDeletingException;
import com.haulmont.testtask.exception.database.DAOEntityReadingException;
import com.haulmont.testtask.exception.database.DAOEntityUpdatingException;

import java.sql.Date;
import java.util.List;

public interface RecipeDAO {
    public void create(Recipe recipe) throws DAOEntityCreationException;

    public Recipe read(Long id) throws DAOEntityReadingException;

    public void update(Recipe patient) throws DAOEntityUpdatingException;

    public void delete(Recipe patient) throws DAOEntityDeletingException;

    public List<Recipe> getAll() throws DAOEntityReadingException;

    public boolean contains(Long id);
}
