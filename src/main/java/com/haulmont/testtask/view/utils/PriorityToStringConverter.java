package com.haulmont.testtask.view.utils;

import com.haulmont.testtask.domain.auxiliary.Priority;
import com.vaadin.data.util.converter.Converter;

import java.util.Locale;

public class PriorityToStringConverter implements Converter<String, Priority> {
    @Override
    public Priority convertToModel(String s, Class<? extends Priority> aClass, Locale locale) throws ConversionException {
        // todo
        return null;
    }

    @Override
    public String convertToPresentation(Priority priority, Class<? extends String> aClass, Locale locale) throws ConversionException {
        return priority != null ? priority.toString() : "null";
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
