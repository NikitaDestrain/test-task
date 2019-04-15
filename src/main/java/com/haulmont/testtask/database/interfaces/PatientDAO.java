package com.haulmont.testtask.database.interfaces;

import com.haulmont.testtask.domain.dto.PatientDTO;
import com.haulmont.testtask.domain.entity.Patient;
import com.haulmont.testtask.exception.database.DAOEntityCreationException;
import com.haulmont.testtask.exception.database.DAOEntityDeletingException;
import com.haulmont.testtask.exception.database.DAOEntityReadingException;
import com.haulmont.testtask.exception.database.DAOEntityUpdatingException;

import java.util.List;

public interface PatientDAO {
    void create(PatientDTO patient) throws DAOEntityCreationException;

    PatientDTO read(Long id) throws DAOEntityReadingException;

    void update(PatientDTO patient) throws DAOEntityUpdatingException;

    void delete(PatientDTO patient) throws DAOEntityDeletingException;

    List<PatientDTO> getAll() throws DAOEntityReadingException;

    boolean contains(Long id);
}
