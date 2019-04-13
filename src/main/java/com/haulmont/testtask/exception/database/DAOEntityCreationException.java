package com.haulmont.testtask.exception.database;

public class DAOEntityCreationException extends Exception {
    public DAOEntityCreationException() {
    }

    public DAOEntityCreationException(String message) {
        super(message);
    }
}
