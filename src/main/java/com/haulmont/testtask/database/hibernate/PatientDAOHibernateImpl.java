package com.haulmont.testtask.database.hibernate;

import com.haulmont.testtask.database.interfaces.PatientDAO;
import com.haulmont.testtask.database.utils.dto.PatientDTOResolver;
import com.haulmont.testtask.database.utils.hibernate.HibernateUtil;
import com.haulmont.testtask.domain.dto.PatientDTO;
import com.haulmont.testtask.domain.entity.Patient;
import com.haulmont.testtask.exception.database.DAOEntityCreationException;
import com.haulmont.testtask.exception.database.DAOEntityDeletingException;
import com.haulmont.testtask.exception.database.DAOEntityReadingException;
import com.haulmont.testtask.exception.database.DAOEntityUpdatingException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PatientDAOHibernateImpl implements PatientDAO {

    private EntityManager entityManager;
    private PatientDTOResolver patientDTOResolver;

    public PatientDAOHibernateImpl() {
        patientDTOResolver = PatientDTOResolver.getInstance();
    }

    @Override
    public void create(Patient patient) throws DAOEntityCreationException {
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(patient);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityCreationException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public PatientDTO read(Long id) throws DAOEntityReadingException {
        PatientDTO patientDto;
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            Patient patient = entityManager.find(Patient.class, id);
            patientDto = patientDTOResolver.resolve(patient);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityReadingException(e.getMessage());
        } finally {
            finishSession();
        }
        return patientDto;
    }

    @Override
    public void update(Patient patient) throws DAOEntityUpdatingException {
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(patient);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityUpdatingException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public void delete(Patient patient) throws DAOEntityDeletingException {
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(patient);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityDeletingException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public List<PatientDTO> getAll() throws DAOEntityReadingException {
        List<PatientDTO> list = new ArrayList<>();
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            for (Patient p : entityManager.createQuery("SELECT p FROM Patient p", Patient.class).getResultList()) {
                list.add(patientDTOResolver.resolve(p));
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
