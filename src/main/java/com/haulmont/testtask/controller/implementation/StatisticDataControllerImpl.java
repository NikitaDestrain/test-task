package com.haulmont.testtask.controller.implementation;

import com.haulmont.testtask.controller.interfaces.RecipeDataController;
import com.haulmont.testtask.controller.interfaces.StatisticDataController;
import com.haulmont.testtask.database.interfaces.RecipeDAO;
import com.haulmont.testtask.domain.auxiliary.DoctorStatistic;
import com.haulmont.testtask.domain.auxiliary.Priority;
import com.haulmont.testtask.domain.dto.DoctorDTO;
import com.haulmont.testtask.domain.dto.RecipeDTO;
import com.haulmont.testtask.domain.dto.StatisticDTO;
import com.haulmont.testtask.exception.controller.DataControllerReadingException;
import com.haulmont.testtask.exception.controller.DataControllerStatisticCreationException;
import com.haulmont.testtask.factory.dto.DoctorStatisticFactory;
import com.haulmont.testtask.factory.dto.StatisticDTOFactory;

import java.util.HashMap;
import java.util.Map;

public class StatisticDataControllerImpl implements StatisticDataController {

    private RecipeDataController recipeDataController;

    public StatisticDataControllerImpl(RecipeDataController recipeDataController) {
        this.recipeDataController = recipeDataController;
    }

    @Override
    public StatisticDTO processStatistic() throws DataControllerStatisticCreationException {
        try {
            Map<Long, DoctorStatistic> resultStatisticMap = new HashMap<>();
            int overallRecipeCount = 0;
            int normalPriorityRecipeCount = 0;
            int citoPriorityRecipeCount = 0;
            int statimPriorityRecipeCount = 0;

            for (RecipeDTO recipe : recipeDataController.getAll()) {
                // init start values for DoctorStatistic
                int initOverallRecipeCount = 1;
                int initNormalPriorityRecipeCount = 0;
                int initCitoPriorityRecipeCount = 0;
                int initStatimPriorityRecipeCount = 0;

                // process priority
                Priority currentPriority = recipe.getPriority();
                if (currentPriority.equals(Priority.NORMAL)) {
                    normalPriorityRecipeCount++;
                    initNormalPriorityRecipeCount++;
                }
                if (currentPriority.equals(Priority.CITO)) {
                    citoPriorityRecipeCount++;
                    initCitoPriorityRecipeCount++;
                }
                if (currentPriority.equals(Priority.STATIM)) {
                    statimPriorityRecipeCount++;
                    initStatimPriorityRecipeCount++;
                }

                // compute statistic values for doctor
                DoctorDTO doctor = recipe.getDoctor();
                Long doctorId = doctor.getId();
                DoctorStatistic currentDoctorStatistic = resultStatisticMap.get(doctorId);
                if (currentDoctorStatistic == null) {
                    DoctorStatistic doctorStatistic = DoctorStatisticFactory.create(
                            doctor,
                            initOverallRecipeCount,
                            initNormalPriorityRecipeCount,
                            initCitoPriorityRecipeCount,
                            initStatimPriorityRecipeCount
                    );
                    resultStatisticMap.put(doctorId, doctorStatistic);
                } else {
                    if (initNormalPriorityRecipeCount != 0) {
                        currentDoctorStatistic.incNormalPriorityRecipeCount();
                    }
                    if (initCitoPriorityRecipeCount != 0) {
                        currentDoctorStatistic.incCitoPriorityRecipeCount();
                    }
                    if (initStatimPriorityRecipeCount != 0) {
                        currentDoctorStatistic.incStatimPriorityRecipeCount();
                    }
                    currentDoctorStatistic.incOverallRecipeCount();
                }

                // inc overall recipe count
                overallRecipeCount++;
            }
            return StatisticDTOFactory.create(
                    resultStatisticMap,
                    overallRecipeCount,
                    normalPriorityRecipeCount,
                    citoPriorityRecipeCount,
                    statimPriorityRecipeCount
            );
        } catch (DataControllerReadingException e) {
            throw new DataControllerStatisticCreationException(e.getMessage());
        }
    }
}
