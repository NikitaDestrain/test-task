package com.haulmont.testtask.factory.dto;

import com.haulmont.testtask.domain.auxiliary.DoctorStatistic;
import com.haulmont.testtask.domain.dto.StatisticDTO;

import java.util.Map;

public class StatisticDTOFactory {
    public static StatisticDTO create(Map<Long, DoctorStatistic> doctorStatisticMap,
                                      int overallRecipeCount,
                                      int normalPriorityRecipeCount,
                                      int citoPriorityRecipeCount,
                                      int statimPriorityRecipeCount) {
        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.setDoctorStatisticMap(doctorStatisticMap);
        statisticDTO.setOverallRecipeCount(overallRecipeCount);
        statisticDTO.setNormalPriorityRecipeCount(normalPriorityRecipeCount);
        statisticDTO.setCitoPriorityRecipeCount(citoPriorityRecipeCount);
        statisticDTO.setStatimPriorityRecipeCount(statimPriorityRecipeCount);
        return statisticDTO;
    }
}
