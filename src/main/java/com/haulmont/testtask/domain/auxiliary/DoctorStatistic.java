package com.haulmont.testtask.domain.auxiliary;

import com.haulmont.testtask.domain.dto.DoctorDTO;
import lombok.Data;

@Data
public class DoctorStatistic {
    private DoctorDTO doctor;
    private int overallRecipeCount;
    private int normalPriorityRecipeCount;
    private int citoPriorityRecipeCount;
    private int statimPriorityRecipeCount;

    public void incOverallRecipeCount() {
        overallRecipeCount++;
    }

    public void incNormalPriorityRecipeCount() {
        normalPriorityRecipeCount++;
    }

    public void incCitoPriorityRecipeCount() {
        citoPriorityRecipeCount++;
    }

    public void incStatimPriorityRecipeCount() {
        statimPriorityRecipeCount++;
    }
}
