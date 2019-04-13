package com.haulmont.testtask.database.hibernate;

import com.haulmont.testtask.database.interfaces.DoctorDAO;
import com.haulmont.testtask.database.utils.HibernateUtil;
import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.exception.database.DAOEntityCreationException;
import com.haulmont.testtask.exception.database.DAOEntityDeletingException;
import com.haulmont.testtask.exception.database.DAOEntityReadingException;
import com.haulmont.testtask.exception.database.DAOEntityUpdatingException;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

public class DoctorDAOHibernateImpl implements DoctorDAO {

    private Session session;

    @Override
    public void create(Doctor doctor) throws DAOEntityCreationException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(doctor);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityCreationException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public Doctor read(Long id) throws DAOEntityReadingException {
        Doctor doctor;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            doctor = session.get(Doctor.class, id);
        } catch (Exception e) {
            throw new DAOEntityReadingException(e.getMessage());
        } finally {
            finishSession();
        }
        return doctor;
    }

    @Override
    public void update(Doctor doctor) throws DAOEntityUpdatingException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(doctor);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityUpdatingException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public void delete(Doctor doctor) throws DAOEntityDeletingException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(doctor);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityDeletingException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public List<Doctor> getAll() throws DAOEntityReadingException {
        List<Doctor> list;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = session.createCriteria(Doctor.class).list();
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
