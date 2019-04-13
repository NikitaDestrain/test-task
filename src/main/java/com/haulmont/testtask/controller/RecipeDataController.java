package com.haulmont.testtask.controller;

public class RecipeDataController {

    private static volatile RecipeDataController instance;

    public static RecipeDataController getInstance() {
        RecipeDataController localInstance = instance;
        if (localInstance == null) {
            synchronized (RecipeDataController.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RecipeDataController();
                }
            }
        }
        return localInstance;
    }
}
