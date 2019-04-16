package com.haulmont.testtask.database.hibernate;

import com.haulmont.testtask.database.interfaces.PatientDAO;
import com.haulmont.testtask.database.utils.dto.PatientResolver;
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
    private PatientResolver patientResolver;

    public PatientDAOHibernateImpl() {
        patientResolver = PatientResolver.getInstance();
    }

    @Override
    public void create(PatientDTO patient) throws DAOEntityCreationException {
        try {
            Patient patientEntity = patientResolver.resolveToEntity(patient);
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(patientEntity);
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
            patientDto = patientResolver.resolveToDTO(patient);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityReadingException(e.getMessage());
        } finally {
            finishSession();
        }
        return patientDto;
    }

    @Override
    public void update(PatientDTO patient) throws DAOEntityUpdatingException {
        try {
            Patient patientEntity = patientResolver.resolveToEntity(patient);
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(patientEntity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityUpdatingException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public void delete(PatientDTO patient) throws DAOEntityDeletingException {
        try {
            Patient patientEntity = patientResolver.resolveToEntity(patient);
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(
                    entityManager.contains(patientEntity) ? patientEntity : entityManager.merge(patientEntity)
            );
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
            for (Patient patient : entityManager.createQuery("SELECT p FROM Patient p", Patient.class).getResultList()) {
                list.add(patientResolver.resolveToDTO(patient));
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
