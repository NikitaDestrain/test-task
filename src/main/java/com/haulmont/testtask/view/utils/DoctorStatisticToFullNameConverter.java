package com.haulmont.testtask.view.utils;

import com.haulmont.testtask.domain.auxiliary.DoctorStatistic;
import com.haulmont.testtask.domain.dto.DoctorDTO;
import com.vaadin.data.util.converter.Converter;

import java.util.Locale;

public class DoctorStatisticToFullNameConverter implements Converter<String, DoctorStatistic> {
    @Override
    public DoctorStatistic convertToModel(String s, Class<? extends DoctorStatistic> aClass, Locale locale) throws ConversionException {
        throw new ConversionException("Operation is not supported");
    }

    @Override
    public String convertToPresentation(DoctorStatistic doctorStatistic, Class<? extends String> aClass, Locale locale) throws ConversionException {
        DoctorDTO doctorDTO = doctorStatistic.getDoctor();
        if (doctorDTO != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(doctorDTO.getSurname());
            sb.append(" ");
            sb.append(doctorDTO.getName().charAt(0));
            sb.append(".");
            if (!doctorDTO.getPatronymic().equals("") && doctorDTO.getPatronymic() != null) {
                sb.append(doctorDTO.getPatronymic().charAt(0));
                sb.append(".");
            }
            sb.append(" (");
            sb.append(doctorDTO.getSpecialization());
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