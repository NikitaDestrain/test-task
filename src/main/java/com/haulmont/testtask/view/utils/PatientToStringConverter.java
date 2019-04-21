package com.haulmont.testtask.view.utils;


import com.haulmont.testtask.domain.dto.PatientDTO;
import com.vaadin.data.util.converter.Converter;

import java.util.Locale;

public class PatientToStringConverter implements Converter<String, PatientDTO> {
    @Override
    public PatientDTO convertToModel(String s, Class<? extends PatientDTO> aClass, Locale locale) throws ConversionException {
        throw new ConversionException("Operation is not supported");
    }

    @Override
    public String convertToPresentation(PatientDTO patientDTO, Class<? extends String> aClass, Locale locale) throws ConversionException {
        if (patientDTO != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(patientDTO.getSurname());
            sb.append(" ");
            sb.append(patientDTO.getName().charAt(0));
            sb.append(".");
            if (!patientDTO.getPatronymic().equals("") && patientDTO.getPatronymic() != null) {
                sb.append(patientDTO.getPatronymic().charAt(0));
                sb.append(".");
            }
            return sb.toString();
        } else {
            return "Unknown";
        }
    }

    @Override
    public Class<PatientDTO> getModelType() {
        return PatientDTO.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}
