package com.haulmont.testtask.database.interfaces;

import com.haulmont.testtask.domain.dto.PatientDTO;
import com.haulmont.testtask.domain.entity.Patient;
import com.haulmont.testtask.exception.database.DAOEntityCreationException;
import com.haulmont.testtask.exception.database.DAOEntityDeletingException;
import com.haulmont.testtask.exception.database.DAOEntityReadingException;
import com.haulmont.testtask.exception.database.DAOEntityUpdatingException;

import java.util.List;

public interface PatientDAO {
    public void create(Patient patient) throws DAOEntityCreationException;

    public PatientDTO read(Long id) throws DAOEntityReadingException;

    public void update(Patient patient) throws DAOEntityUpdatingException;

    public void delete(Patient patient) throws DAOEntityDeletingException;

    public List<PatientDTO> getAll() throws DAOEntityReadingException;

    public boolean contains(Long id);
}
