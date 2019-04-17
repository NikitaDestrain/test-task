package com.haulmont.testtask.view.utils;

import com.haulmont.testtask.domain.dto.DoctorDTO;
import com.vaadin.data.util.converter.Converter;

import java.util.Locale;

public class DoctorToStringConverter implements Converter<String, DoctorDTO> {
    @Override
    public DoctorDTO convertToModel(String s, Class<? extends DoctorDTO> aClass, Locale locale) throws ConversionException {
        throw new ConversionException("Operation is not supported");
    }

    @Override
    public String convertToPresentation(DoctorDTO doctorDTO, Class<? extends String> aClass, Locale locale) throws ConversionException {
        return doctorDTO != null ? doctorDTO.getName() : "null";
    }

    @Override
    public Class<DoctorDTO> getModelType() {
        return DoctorDTO.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}
