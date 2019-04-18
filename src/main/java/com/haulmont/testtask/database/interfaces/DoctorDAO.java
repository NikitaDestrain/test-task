package com.haulmont.testtask.database.interfaces;

import com.haulmont.testtask.domain.dto.DoctorDTO;
import com.haulmont.testtask.domain.dto.DoctorStatisticDTO;
import com.haulmont.testtask.domain.entity.Doctor;
import com.haulmont.testtask.exception.database.DAOEntityCreationException;
import com.haulmont.testtask.exception.database.DAOEntityDeletingException;
import com.haulmont.testtask.exception.database.DAOEntityReadingException;
import com.haulmont.testtask.exception.database.DAOEntityUpdatingException;

import java.util.List;

public interface DoctorDAO {
    void create(DoctorDTO doctor) throws DAOEntityCreationException;

    DoctorDTO read(Long id) throws DAOEntityReadingException;

    void update(DoctorDTO doctor) throws DAOEntityUpdatingException;

    void delete(DoctorDTO doctor) throws DAOEntityDeletingException;

    List<DoctorDTO> getAll() throws DAOEntityReadingException;

    boolean contains(Long id);

    DoctorStatisticDTO readStatistic(Long id) throws DAOEntityReadingException;

    List<DoctorStatisticDTO> readStatisticForAll() throws DAOEntityReadingException;
}
