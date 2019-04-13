package com.haulmont.testtask.controller;

public class DoctorDataController {

    private static volatile DoctorDataController instance;

    public static DoctorDataController getInstance() {
        DoctorDataController localInstance = instance;
        if (localInstance == null) {
            synchronized (DoctorDataController.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DoctorDataController();
                }
            }
        }
        return localInstance;
    }

    public void create() {

    }
}
