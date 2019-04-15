package com.haulmont.testtask.database.utils.hibernate;

import com.haulmont.testtask.domain.auxiliary.Priority;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.haulmont.testtask.database.utils.hibernate.HibernateUtilConstants.*;

@Converter
public class PriorityConverter implements AttributeConverter<Priority, String> {
    @Override
    public String convertToDatabaseColumn(Priority priority) {
        switch (priority) {
            case NORMAL:
                return NORMAL_PRIORITY_RUSSIAN;
            case CITO:
                return CITO_PRIORITY_RUSSIAN;
            case STATIM:
                return STATIM_PRIORITY_RUSSIAN;
            default:
                return NORMAL_PRIORITY_RUSSIAN;
        }
    }

    @Override
    public Priority convertToEntityAttribute(String s) {
        switch (s) {
            case NORMAL_PRIORITY_RUSSIAN:
                return Priority.NORMAL;
            case CITO_PRIORITY_RUSSIAN:
                return Priority.CITO;
            case STATIM_PRIORITY_RUSSIAN:
                return Priority.STATIM;
            default:
                return Priority.NORMAL;
        }
    }
}
