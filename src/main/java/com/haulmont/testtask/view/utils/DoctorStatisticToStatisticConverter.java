package com.haulmont.testtask.view.utils;

import com.haulmont.testtask.domain.auxiliary.DoctorStatistic;
import com.vaadin.data.util.converter.Converter;

import java.util.Locale;

public class DoctorStatisticToStatisticConverter implements Converter<String, DoctorStatistic> {
    @Override
    public DoctorStatistic convertToModel(String s, Class<? extends DoctorStatistic> aClass, Locale locale) throws ConversionException {
        throw new ConversionException("Operation is not supported");
    }

    @Override
    public String convertToPresentation(DoctorStatistic doctorStatistic, Class<? extends String> aClass, Locale locale) throws ConversionException {
        if (doctorStatistic != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(doctorStatistic.getOverallRecipeCount());
            sb.append(" (Normal: ");
            sb.append(doctorStatistic.getNormalPriorityRecipeCount());
            sb.append("; Cito: ");
            sb.append(doctorStatistic.getCitoPriorityRecipeCount());
            sb.append("; Statim: ");
            sb.append(doctorStatistic.getStatimPriorityRecipeCount());
            sb.append(")");
            return sb.toString();
        } else {
            return "Unknown";
        }
    }

    @Override
    public Class<DoctorStatistic> getModelType() {
        return DoctorStatistic.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}