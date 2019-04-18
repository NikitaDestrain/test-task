package com.haulmont.testtask.view.utils;

import com.haulmont.testtask.database.utils.hibernate.PriorityConverter;
import com.haulmont.testtask.domain.auxiliary.Priority;
import com.vaadin.data.util.converter.Converter;

import java.util.Locale;

public class PriorityToStringConverter implements Converter<String, Priority> {
    @Override
    public Priority convertToModel(String s, Class<? extends Priority> aClass, Locale locale) throws ConversionException {
        throw new ConversionException("Operation is not supported");
    }

    @Override
    public String convertToPresentation(Priority priority, Class<? extends String> aClass, Locale locale) throws ConversionException {
        if (priority != null) {
            return new PriorityConverter().convertToViewValue(priority);
        } else {
            return "Unknown";
        }
    }

    @Override
    public Class<Priority> getModelType() {
        return Priority.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}
