package com.haulmont.testtask.database.hibernate;

import com.haulmont.testtask.database.interfaces.DoctorDAO;
import com.haulmont.testtask.database.utils.dto.DoctorDTOResolver;
import com.haulmont.testtask.database.utils.hibernate.HibernateUtil;
import com.haulmont.testtask.domain.dto.DoctorDTO;
import com.haulmont.testtask.domain.entity.Doctor;
import com.haulmont.testtask.exception.database.DAOEntityCreationException;
import com.haulmont.testtask.exception.database.DAOEntityDeletingException;
import com.haulmont.testtask.exception.database.DAOEntityReadingException;
import com.haulmont.testtask.exception.database.DAOEntityUpdatingException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DoctorDAOHibernateImpl implements DoctorDAO {

    private EntityManager entityManager;
    private DoctorDTOResolver doctorDTOResolver;

    public DoctorDAOHibernateImpl() {
        doctorDTOResolver = DoctorDTOResolver.getInstance();
    }

    @Override
    public void create(Doctor doctor) throws DAOEntityCreationException {
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(doctor);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityCreationException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public DoctorDTO read(Long id) throws DAOEntityReadingException {
        DoctorDTO doctorDto;
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            Doctor doctor = entityManager.find(Doctor.class, id);
            doctorDto = doctorDTOResolver.resolve(doctor);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityReadingException(e.getMessage());
        } finally {
            finishSession();
        }
        return doctorDto;
    }

    @Override
    public void update(Doctor doctor) throws DAOEntityUpdatingException {
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(doctor);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityUpdatingException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public void delete(Doctor doctor) throws DAOEntityDeletingException {
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(doctor);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOEntityDeletingException(e.getMessage());
        } finally {
            finishSession();
        }
    }

    @Override
    public List<DoctorDTO> getAll() throws DAOEntityReadingException {
        List<DoctorDTO> list = new ArrayList<>();
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            for (Doctor d : entityManager.createQuery("SELECT d FROM Doctor d", Doctor.class).getResultList()) {
                list.add(doctorDTOResolver.resolve(d));
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
