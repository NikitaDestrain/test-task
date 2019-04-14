package com.haulmont.testtask.database.interfaces;

import com.haulmont.testtask.domain.dto.DoctorDTO;
import com.haulmont.testtask.domain.entity.Doctor;
import com.haulmont.testtask.exception.database.DAOEntityCreationException;
import com.haulmont.testtask.exception.database.DAOEntityDeletingException;
import com.haulmont.testtask.exception.database.DAOEntityReadingException;
import com.haulmont.testtask.exception.database.DAOEntityUpdatingException;

import java.util.List;

public interface DoctorDAO {
    public void create(Doctor doctor) throws DAOEntityCreationException;

    public DoctorDTO read(Long id) throws DAOEntityReadingException;

    public void update(Doctor doctor) throws DAOEntityUpdatingException;

    public void delete(Doctor doctor) throws DAOEntityDeletingException;

    public List<DoctorDTO> getAll() throws DAOEntityReadingException;

    public boolean contains(Long id);
}
