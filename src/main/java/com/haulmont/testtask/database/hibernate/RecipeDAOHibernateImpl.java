package com.haulmont.testtask.database.hibernate;

import com.haulmont.testtask.database.interfaces.RecipeDAO;
import com.haulmont.testtask.database.utils.dto.RecipeDTOResolver;
import com.haulmont.testtask.database.utils.hibernate.HibernateUtil;
import com.haulmont.testtask.domain.dto.RecipeDTO;
import com.haulmont.testtask.domain.entity.Recipe;
import com.haulmont.testtask.exception.database.DAOEntityCreationException;
import com.haulmont.testtask.exception.database.DAOEntityDeletingException;
import com.haulmont.testtask.exception.database.DAOEntityReadingException;
import com.haulmont.testtask.exception.database.DAOEntityUpdatingException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeDAOHibernateImpl implements RecipeDAO {

    private EntityManager entityManager;
    private RecipeDTOResolver recipeDTOResolver;

    public RecipeDAOHibernateImpl() {
        recipeDTOResolver = RecipeDTOResolver.getInstance();
    }

    @Override
    public void create(Recipe recipe) throws DAOEntityCreationException {
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(recipe);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityCreationException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public RecipeDTO read(Long id) throws DAOEntityReadingException {
        RecipeDTO recipeDto;
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            Recipe recipe = entityManager.find(Recipe.class, id);
            recipeDto = recipeDTOResolver.resolve(recipe);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityReadingException(e.getMessage());
        } finally {
            finishSession();
        }
        return recipeDto;
    }

    @Override
    public void update(Recipe recipe) throws DAOEntityUpdatingException {
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(recipe);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityUpdatingException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public void delete(Recipe recipe) throws DAOEntityDeletingException {
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(recipe);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityDeletingException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public List<RecipeDTO> getAll() throws DAOEntityReadingException {
        List<RecipeDTO> list = new ArrayList<>();
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            for (Recipe r : entityManager.createQuery("SELECT r FROM Recipe r", Recipe.class).getResultList()) {
                list.add(recipeDTOResolver.resolve(r));
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityReadingException(e.getMessage());
        } finally {
            finishSession();
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public boolean contains(Long id) {
        try {
            return read(id) != null;
        } catch (Exception e) {
            return false;
        } finally {
            finishSession();
        }
    }

    private void finishSession() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
