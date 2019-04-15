package com.haulmont.testtask.database.hibernate;

import com.haulmont.testtask.database.interfaces.DAOManager;
import com.haulmont.testtask.database.interfaces.DoctorDAO;
import com.haulmont.testtask.database.interfaces.PatientDAO;
import com.haulmont.testtask.database.interfaces.RecipeDAO;

public class DAOManagerHibernateImpl implements DAOManager {

    private static volatile DAOManagerHibernateImpl instance;
    private DoctorDAO doctorDAO;
    private PatientDAO patientDAO;
    private RecipeDAO recipeDAO;

    private DAOManagerHibernateImpl() {
    }

    public static DAOManagerHibernateImpl getInstance() {
        DAOManagerHibernateImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (DAOManagerHibernateImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DAOManagerHibernateImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public DoctorDAO getDoctorDao() {
        if (doctorDAO == null) doctorDAO = new DoctorDAOHibernateImpl();
        return doctorDAO;
    }

    @Override
    public PatientDAO getPatientDao() {
        if (patientDAO == null) patientDAO = new PatientDAOHibernateImpl();
        return patientDAO;
    }

    @Override
    public RecipeDAO getRecipeDao() {
        if (recipeDAO == null) recipeDAO = new RecipeDAOHibernateImpl();
        return recipeDAO;
    }
}
