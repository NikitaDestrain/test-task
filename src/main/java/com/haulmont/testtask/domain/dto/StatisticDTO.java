package com.haulmont.testtask.domain.dto;

import com.haulmont.testtask.domain.auxiliary.DoctorStatistic;
import lombok.Data;

import java.util.Map;

@Data
public class StatisticDTO {
    private int overallRecipeCount;
    private int normalPriorityRecipeCount;
    private int citoPriorityRecipeCount;
    private int statimPriorityRecipeCount;
    private Map<Long, DoctorStatistic> doctorStatisticMap;
}
