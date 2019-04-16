package com.haulmont.testtask.database.hibernate;

import com.haulmont.testtask.database.interfaces.RecipeDAO;
import com.haulmont.testtask.database.utils.dto.RecipeResolver;
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
    private RecipeResolver recipeResolver;

    public RecipeDAOHibernateImpl() {
        recipeResolver = RecipeResolver.getInstance();
    }

    @Override
    public void create(RecipeDTO recipe) throws DAOEntityCreationException {
        try {
            Recipe recipeEntity = recipeResolver.resolveToEntity(recipe);
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(recipeEntity);
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
            recipeDto = recipeResolver.resolveToDTO(recipe);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityReadingException(e.getMessage());
        } finally {
            finishSession();
        }
        return recipeDto;
    }

    @Override
    public void update(RecipeDTO recipe) throws DAOEntityUpdatingException {
        try {
            Recipe recipeEntity = recipeResolver.resolveToEntity(recipe);
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(recipeEntity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityUpdatingException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public void delete(RecipeDTO recipe) throws DAOEntityDeletingException {
        try {
            Recipe recipeEntity = recipeResolver.resolveToEntity(recipe);
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(
                    entityManager.contains(recipeEntity) ? recipeEntity : entityManager.merge(recipeEntity)
            );
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
            for (Recipe recipe : entityManager.createQuery("SELECT r FROM Recipe r", Recipe.class).getResultList()) {
                list.add(recipeResolver.resolveToDTO(recipe));
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
