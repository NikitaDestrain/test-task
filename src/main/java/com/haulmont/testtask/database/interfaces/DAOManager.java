package com.haulmont.testtask.database.interfaces;

public interface DAOManager {
    public DoctorDAO getDoctorDao();

    public PatientDAO getPatientDao();

    public RecipeDAO getRecipeDao();
}
