package com.haulmont.testtask.view.utils;

import com.haulmont.testtask.domain.dto.StatisticDTO;
import com.vaadin.data.util.converter.Converter;

import java.util.Locale;

public class StatisticToStringConverter implements Converter<String, StatisticDTO> {
    @Override
    public StatisticDTO convertToModel(String s, Class<? extends StatisticDTO> aClass, Locale locale) throws ConversionException {
        throw new Converter.ConversionException("Operation is not supported");
    }

    @Override
    public String convertToPresentation(StatisticDTO statisticDTO, Class<? extends String> aClass, Locale locale) throws ConversionException {
        if (statisticDTO != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(statisticDTO.getOverallRecipeCount());
            sb.append(" (Normal: ");
            sb.append(statisticDTO.getNormalPriorityRecipeCount());
            sb.append("; Cito: ");
            sb.append(statisticDTO.getCitoPriorityRecipeCount());
            sb.append("; Statim: ");
            sb.append(statisticDTO.getStatimPriorityRecipeCount());
            sb.append(")");
            return sb.toString();
        } else {
            return "Unknown";
        }
    }

    @Override
    public Class<StatisticDTO> getModelType() {
        return StatisticDTO.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}