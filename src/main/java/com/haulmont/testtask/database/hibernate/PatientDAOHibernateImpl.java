package com.haulmont.testtask.database.hibernate;

import com.haulmont.testtask.database.interfaces.PatientDAO;
import com.haulmont.testtask.database.utils.HibernateUtil;
import com.haulmont.testtask.domain.Patient;
import com.haulmont.testtask.exception.database.DAOEntityCreationException;
import com.haulmont.testtask.exception.database.DAOEntityDeletingException;
import com.haulmont.testtask.exception.database.DAOEntityReadingException;
import com.haulmont.testtask.exception.database.DAOEntityUpdatingException;
import com.haulmont.testtask.factory.PatientFactory;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

public class PatientDAOHibernateImpl implements PatientDAO {

    private Session session;

    @Override
    public void create(Patient patient) throws DAOEntityCreationException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(patient);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityCreationException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public Patient read(Long id) throws DAOEntityReadingException {
        Patient patient;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            patient = session.get(Patient.class, id);
        } catch (Exception e) {
            throw new DAOEntityReadingException(e.getMessage());
        } finally {
            finishSession();
        }
        return patient;
    }

    @Override
    public void update(Patient patient) throws DAOEntityUpdatingException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(patient);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityUpdatingException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public void delete(Patient patient) throws DAOEntityDeletingException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(patient);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityDeletingException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public List<Patient> getAll() throws DAOEntityReadingException {
        List<Patient> list;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = session.createCriteria(Patient.class).list();
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
