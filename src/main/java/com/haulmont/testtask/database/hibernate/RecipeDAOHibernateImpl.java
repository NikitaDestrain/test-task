package com.haulmont.testtask.database.hibernate;

import com.haulmont.testtask.database.interfaces.RecipeDAO;
import com.haulmont.testtask.database.utils.HibernateUtil;
import com.haulmont.testtask.domain.Recipe;
import com.haulmont.testtask.exception.database.DAOEntityCreationException;
import com.haulmont.testtask.exception.database.DAOEntityDeletingException;
import com.haulmont.testtask.exception.database.DAOEntityReadingException;
import com.haulmont.testtask.exception.database.DAOEntityUpdatingException;
import com.haulmont.testtask.factory.RecipeFactory;
import org.hibernate.Session;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

public class RecipeDAOHibernateImpl implements RecipeDAO {

    private Session session;

    @Override
    public void create(Recipe recipe) throws DAOEntityCreationException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(recipe);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityCreationException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public Recipe read(Long id) throws DAOEntityReadingException {
        Recipe recipe;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            recipe = session.get(Recipe.class, id);
        } catch (Exception e) {
            throw new DAOEntityReadingException(e.getMessage());
        } finally {
            finishSession();
        }
        return recipe;
    }

    @Override
    public void update(Recipe recipe) throws DAOEntityUpdatingException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(recipe);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityUpdatingException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public void delete(Recipe recipe) throws DAOEntityDeletingException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(recipe);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityDeletingException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public List<Recipe> getAll() throws DAOEntityReadingException {
        List<Recipe> list;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = session.createCriteria(Recipe.class).list();
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
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
