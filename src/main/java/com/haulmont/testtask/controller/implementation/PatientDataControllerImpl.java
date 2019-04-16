package com.haulmont.testtask.controller.implementation;

import com.haulmont.testtask.controller.interfaces.PatientDataController;
import com.haulmont.testtask.database.interfaces.PatientDAO;
import com.haulmont.testtask.domain.dto.PatientDTO;
import com.haulmont.testtask.exception.controller.DataControllerCreationException;
import com.haulmont.testtask.exception.controller.DataControllerReadingException;
import com.haulmont.testtask.exception.controller.DataControllerRemovingException;
import com.haulmont.testtask.exception.controller.DataControllerUpdatingException;
import com.haulmont.testtask.exception.database.DAOEntityCreationException;
import com.haulmont.testtask.exception.database.DAOEntityDeletingException;
import com.haulmont.testtask.exception.database.DAOEntityReadingException;
import com.haulmont.testtask.exception.database.DAOEntityUpdatingException;

import java.util.List;

public class PatientDataControllerImpl implements PatientDataController {

    private PatientDAO patientDAO;

    public PatientDataControllerImpl(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Override
    public void create(PatientDTO patient) throws DataControllerCreationException {
        try {
            patientDAO.create(patient);
        } catch (DAOEntityCreationException e) {
            throw new DataControllerCreationException(e.getMessage());
        }
    }

    @Override
    public PatientDTO get(Long id) throws DataControllerReadingException {
        try {
            return patientDAO.read(id);
        } catch (DAOEntityReadingException e) {
            throw new DataControllerReadingException(e.getMessage());
        }
    }

    @Override
    public void update(PatientDTO patient) throws DataControllerUpdatingException {
        try {
            patientDAO.update(patient);
        } catch (DAOEntityUpdatingException e) {
            throw new DataControllerUpdatingException(e.getMessage());
        }
    }

    @Override
    public void remove(Long id) throws DataControllerRemovingException {
        try {
            PatientDTO patient = patientDAO.read(id);
            patientDAO.delete(patient);
        } catch (DAOEntityDeletingException | DAOEntityReadingException e) {
            throw new DataControllerRemovingException(e.getMessage());
        }
    }

    @Override
    public List<PatientDTO> getAll() throws DataControllerReadingException {
        try {
            return patientDAO.getAll();
        } catch (DAOEntityReadingException e) {
            throw new DataControllerReadingException(e.getMessage());
        }
    }
}
