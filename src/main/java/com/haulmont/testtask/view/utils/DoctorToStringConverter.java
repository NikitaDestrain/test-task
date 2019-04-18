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
    public Class<DoctorDTO> getModelType() {
        return DoctorDTO.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}
