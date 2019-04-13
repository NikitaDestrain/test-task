package com.haulmont.testtask.database.utils;

import com.haulmont.testtask.exception.database.DAOEntityCreationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.beans.Statement;
import java.io.FileInputStream;
import java.util.Scanner;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() throws ExceptionInInitializerError {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() throws ExceptionInInitializerError, DAOEntityCreationException {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
            executeSqlStartScript();
        }
        return sessionFactory;
    }

    // fixme
    private static void executeSqlStartScript() throws DAOEntityCreationException {
        String delimiter = HibernateUtilConstants.SCRIPT_DELIMITER;
        Scanner scanner;
        Session session;
        try {
            scanner = new Scanner(new FileInputStream(HibernateUtilConstants.START_SCRIPT_PATH)).useDelimiter(delimiter);
            session = sessionFactory.openSession();
            while (scanner.hasNext()) {
                String sql = scanner.next() + delimiter;
                session.beginTransaction();
                session.createSQLQuery(sql).executeUpdate();
            }
            session.getTransaction().commit();
            session.close();
            scanner.close();
            /*
            scanner = new Scanner(new FileInputStream(HibernateUtilConstants.START_DATA_PATH)).useDelimiter(delimiter);
            session = sessionFactory.openSession();
            while (scanner.hasNext()) {
                String sql = scanner.next() + delimiter;
                session.beginTransaction();
                session.createSQLQuery(sql).executeUpdate();
            }
            session.getTransaction().commit();
            session.close();
            scanner.close();*/
        } catch (Exception e) {
            throw new DAOEntityCreationException(e.getMessage());
        }
    }
}
