package com.haulmont.testtask.database.interfaces;

import com.haulmont.testtask.domain.dto.RecipeDTO;
import com.haulmont.testtask.domain.entity.Recipe;
import com.haulmont.testtask.exception.database.DAOEntityCreationException;
import com.haulmont.testtask.exception.database.DAOEntityDeletingException;
import com.haulmont.testtask.exception.database.DAOEntityReadingException;
import com.haulmont.testtask.exception.database.DAOEntityUpdatingException;

import java.util.List;

public interface RecipeDAO {
    void create(RecipeDTO recipe) throws DAOEntityCreationException;

    RecipeDTO read(Long id) throws DAOEntityReadingException;

    void update(RecipeDTO patient) throws DAOEntityUpdatingException;

    void delete(RecipeDTO patient) throws DAOEntityDeletingException;

    List<RecipeDTO> getAll() throws DAOEntityReadingException;

    boolean contains(Long id);
}
