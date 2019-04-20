package com.haulmont.testtask.controller.interfaces;

public interface DataControllerManager {
    DoctorDataController getDoctorDataController();

    PatientDataController getPatientDataController();

    RecipeDataController getRecipeDataController();

    StatisticDataController getStatisticDataController();
}
