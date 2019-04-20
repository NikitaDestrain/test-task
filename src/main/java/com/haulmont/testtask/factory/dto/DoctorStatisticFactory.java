package com.haulmont.testtask.factory.dto;

import com.haulmont.testtask.domain.auxiliary.DoctorStatistic;
import com.haulmont.testtask.domain.dto.DoctorDTO;

public class DoctorStatisticFactory {
    public static DoctorStatistic create(DoctorDTO doctor,
                                         int overallRecipeCount,
                                         int normalPriorityRecipeCount,
                                         int citoPriorityRecipeCount,
                                         int statimPriorityRecipeCount) {
        DoctorStatistic doctorStatistic = new DoctorStatistic();
        doctorStatistic.setDoctor(doctor);
        doctorStatistic.setOverallRecipeCount(overallRecipeCount);
        doctorStatistic.setNormalPriorityRecipeCount(normalPriorityRecipeCount);
        doctorStatistic.setCitoPriorityRecipeCount(citoPriorityRecipeCount);
        doctorStatistic.setStatimPriorityRecipeCount(statimPriorityRecipeCount);
        return doctorStatistic;
    }
}
