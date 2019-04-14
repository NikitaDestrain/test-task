package com.haulmont.testtask.database.utils.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static EntityManager entityManager;

    private static EntityManager createEntityManager() throws ExceptionInInitializerError {
        try {
            return Persistence.createEntityManagerFactory(HibernateUtilConstants.PERSISTENT_UNIT_NAME).createEntityManager();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null) entityManager = createEntityManager();
        return entityManager;
    }
}
