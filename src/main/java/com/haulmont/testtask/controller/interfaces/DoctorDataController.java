package com.haulmont.testtask.controller.interfaces;

import com.haulmont.testtask.domain.dto.DoctorDTO;
import com.haulmont.testtask.domain.dto.DoctorStatisticDTO;
import com.haulmont.testtask.exception.controller.DataControllerCreationException;
import com.haulmont.testtask.exception.controller.DataControllerReadingException;
import com.haulmont.testtask.exception.controller.DataControllerRemovingException;
import com.haulmont.testtask.exception.controller.DataControllerUpdatingException;

import java.util.List;

public interface DoctorDataController {
    void create(DoctorDTO doctor) throws DataControllerCreationException;

    DoctorDTO get(Long id) throws DataControllerReadingException;

    void update(DoctorDTO doctor) throws DataControllerUpdatingException;

    void remove(Long id) throws DataControllerRemovingException;

    List<DoctorDTO> getAll() throws DataControllerReadingException;

    DoctorStatisticDTO getStatisticForId(Long id) throws DataControllerReadingException;

    List<DoctorStatisticDTO> getStatisticForAll() throws DataControllerReadingException;
}
