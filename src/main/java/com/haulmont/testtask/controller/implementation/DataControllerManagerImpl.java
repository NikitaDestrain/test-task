package com.haulmont.testtask.controller.implementation;

import com.haulmont.testtask.controller.interfaces.DataControllerManager;
import com.haulmont.testtask.controller.interfaces.DoctorDataController;
import com.haulmont.testtask.controller.interfaces.PatientDataController;
import com.haulmont.testtask.controller.interfaces.RecipeDataController;
import com.haulmont.testtask.database.hibernate.DAOManagerHibernateImpl;

public class DataControllerManagerImpl implements DataControllerManager {

    private static volatile DataControllerManagerImpl instance;
    private DAOManagerHibernateImpl daoManagerHibernate;
    private DoctorDataController doctorDataController;
    private PatientDataController patientDataController;
    private RecipeDataController recipeDataController;

    private DataControllerManagerImpl() {
        daoManagerHibernate = DAOManagerHibernateImpl.getInstance();
    }

    public static DataControllerManagerImpl getInstance() {
        DataControllerManagerImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (DataControllerManagerImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DataControllerManagerImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public DoctorDataController getDoctorDataController() {
        if (doctorDataController == null) doctorDataController = new DoctorDataControllerImpl(
                daoManagerHibernate.getDoctorDao()
        );
        return doctorDataController;
    }

    @Override
    public PatientDataController getPatientDataController() {
        if (patientDataController == null) patientDataController = new PatientDataControllerImpl(
                daoManagerHibernate.getPatientDao()
        );
        return patientDataController;
    }

    @Override
    public RecipeDataController getRecipeDataController() {
        if (recipeDataController == null) recipeDataController = new RecipeDataControllerImpl(
                daoManagerHibernate.getRecipeDao()
        );
        return recipeDataController;
    }
}
