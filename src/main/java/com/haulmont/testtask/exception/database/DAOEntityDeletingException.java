package com.haulmont.testtask.exception.database;

public class DAOEntityDeletingException extends Exception {
    public DAOEntityDeletingException() {
    }

    public DAOEntityDeletingException(String message) {
        super(message);
    }
}
