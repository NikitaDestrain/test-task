package com.haulmont.testtask.controller.interfaces;

import com.haulmont.testtask.domain.dto.PatientDTO;
import com.haulmont.testtask.exception.controller.DataControllerCreationException;
import com.haulmont.testtask.exception.controller.DataControllerReadingException;
import com.haulmont.testtask.exception.controller.DataControllerUpdatingException;

import java.util.List;

public interface PatientDataController {
    void create(PatientDTO patient) throws DataControllerCreationException;

    PatientDTO get(Long id) throws DataControllerReadingException;

    void update(PatientDTO patient) throws DataControllerUpdatingException;

    List<PatientDTO> getAll() throws DataControllerReadingException;
}
