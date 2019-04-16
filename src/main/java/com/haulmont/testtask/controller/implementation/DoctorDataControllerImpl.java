package com.haulmont.testtask.controller.implementation;

import com.haulmont.testtask.controller.interfaces.DoctorDataController;
import com.haulmont.testtask.database.interfaces.DoctorDAO;
import com.haulmont.testtask.domain.dto.DoctorDTO;
import com.haulmont.testtask.domain.dto.DoctorStatisticDTO;
import com.haulmont.testtask.exception.controller.DataControllerCreationException;
import com.haulmont.testtask.exception.controller.DataControllerReadingException;
import com.haulmont.testtask.exception.controller.DataControllerRemovingException;
import com.haulmont.testtask.exception.controller.DataControllerUpdatingException;
import com.haulmont.testtask.exception.database.DAOEntityCreationException;
import com.haulmont.testtask.exception.database.DAOEntityDeletingException;
import com.haulmont.testtask.exception.database.DAOEntityReadingException;
import com.haulmont.testtask.exception.database.DAOEntityUpdatingException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Logger;

public class DoctorDataControllerImpl implements DoctorDataController {

    private DoctorDAO doctorDAO;
    private Logger logger = Logger.getLogger(DoctorDataControllerImpl.class.getName());

    public DoctorDataControllerImpl(DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    @Override
    public void create(DoctorDTO doctor) throws DataControllerCreationException {
        try {
            doctorDAO.create(doctor);
        } catch (DAOEntityCreationException e) {
            throw new DataControllerCreationException(e.getMessage());
        }
    }

    @Override
    public DoctorDTO get(Long id) throws DataControllerReadingException {
        try {
            return doctorDAO.read(id);
        } catch (DAOEntityReadingException e) {
            throw new DataControllerReadingException(e.getMessage());
        }
    }

    @Override
    public void update(DoctorDTO doctor) throws DataControllerUpdatingException {
        try {
            doctorDAO.update(doctor);
        } catch (DAOEntityUpdatingException e) {
            throw new DataControllerUpdatingException(e.getMessage());
        }
    }

    @Override
    public void remove(Long id) throws DataControllerRemovingException {
        try {
            DoctorDTO doctor = doctorDAO.read(id);
            doctorDAO.delete(doctor);
        } catch (DAOEntityDeletingException | DAOEntityReadingException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            logger.severe(sw.toString());
            throw new DataControllerRemovingException(e.getMessage());
        }
    }

    @Override
    public List<DoctorDTO> getAll() throws DataControllerReadingException {
        try {
            return doctorDAO.getAll();
        } catch (DAOEntityReadingException e) {
            throw new DataControllerReadingException(e.getMessage());
        }
    }

    @Override
    public DoctorStatisticDTO getStatisticForId() throws DataControllerReadingException {
        return null;
    }
}
