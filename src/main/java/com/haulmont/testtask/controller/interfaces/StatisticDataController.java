package com.haulmont.testtask.controller.interfaces;

import com.haulmont.testtask.domain.dto.StatisticDTO;
import com.haulmont.testtask.exception.controller.DataControllerStatisticCreationException;

public interface StatisticDataController {
    StatisticDTO processStatistic() throws DataControllerStatisticCreationException;
}
