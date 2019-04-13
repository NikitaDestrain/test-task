package com.haulmont.testtask.controller;

public class PatientDataController {

    private static volatile PatientDataController instance;

    public static PatientDataController getInstance() {
        PatientDataController localInstance = instance;
        if (localInstance == null) {
            synchronized (PatientDataController.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PatientDataController();
                }
            }
        }
        return localInstance;
    }
}
