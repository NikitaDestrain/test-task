package com.haulmont.testtask.database.utils.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static EntityManagerFactory entityManagerFactory;

    private static EntityManagerFactory createEntityManagerFactory() throws ExceptionInInitializerError {
        try {
            return Persistence.createEntityManagerFactory(HibernateUtilConstants.PERSISTENT_UNIT_NAME);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) entityManagerFactory = createEntityManagerFactory();
        return entityManagerFactory.createEntityManager();
    }
}
