package com.haulmont.testtask.view.ui;

import com.haulmont.testtask.controller.implementation.DataControllerManagerImpl;
import com.haulmont.testtask.controller.interfaces.DataControllerManager;
import com.haulmont.testtask.view.index.HomeView;
import com.haulmont.testtask.view.sub.DoctorView;
import com.haulmont.testtask.view.sub.PatientView;
import com.haulmont.testtask.view.sub.RecipeView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.MenuBar;


@Theme(UIHelper.THEME_NAME)
public class MainUI extends UI {
    private static final String PAGE_TITLE = "Hospital Recipe System";

    private static final String HEADER_WIDTH = "100%";
    private static final int HEADER_EXPAND_RATIO = 1;

    private static final String SHOW_LAYOUT_WIDTH = "100%";
    private static final String SHOW_LAYOUT_HEIGHT = "100%";
    private static final int SHOW_LAYOUT_EXPAND_RATIO = 1;

    private static final String MAIN_LAYOUT_WIDTH = "100%";

    private static final String MENU_HOME_VIEW_NAME = "";
    private static final String MENU_DOCTORS_VIEW_NAME = "doctors";
    private static final String MENU_PATIENTS_VIEW_NAME = "patients";
    private static final String MENU_RECIPES_VIEW_NAME = "recipes";

    private static final String MENU_HOME_NAME = "Home";
    private static final String MENU_DOCTORS_NAME = "Doctors";
    private static final String MENU_PATIENTS_NAME = "Patients";
    private static final String MENU_RECIPES_NAME = "Recipes";

    private HorizontalLayout header;
    private VerticalLayout showLayout;
    private VerticalLayout mainLayout;
    private Navigator viewNavigator;
    private ViewDisplay viewDisplay;
    private MenuBar menuBar;

    private DataControllerManager dataControllerManager;

    public MainUI() {
        dataControllerManager = DataControllerManagerImpl.getInstance();
    }

    private void initHeader() {
        header = new HorizontalLayout();
        header.setWidth(HEADER_WIDTH);
        header.setMargin(false);
        header.setSpacing(true);
    }

    private void initShowLayout() {
        showLayout = new VerticalLayout();
        showLayout.setWidth(SHOW_LAYOUT_WIDTH);
        showLayout.setHeight(SHOW_LAYOUT_HEIGHT);
    }

    private void initMainLayout() {
        mainLayout = new VerticalLayout();
        mainLayout.setHeight(MAIN_LAYOUT_WIDTH);
        mainLayout.addComponents(header, showLayout);
        mainLayout.setComponentAlignment(header, Alignment.TOP_CENTER);
        mainLayout.setComponentAlignment(showLayout, Alignment.MIDDLE_CENTER);
        //mainLayout.setExpandRatio(header, HEADER_EXPAND_RATIO);
        mainLayout.setExpandRatio(showLayout, SHOW_LAYOUT_EXPAND_RATIO);
    }

    private void initViewDisplay() {
        this.viewDisplay = new Navigator.ComponentContainerViewDisplay(showLayout);
    }

    private void initViewNavigator() {
        viewNavigator = new Navigator(this, this.viewDisplay);
        viewNavigator.addView(MENU_HOME_VIEW_NAME, new HomeView());
        viewNavigator.addView(MENU_DOCTORS_VIEW_NAME, new DoctorView(dataControllerManager.getDoctorDataController(),
                dataControllerManager.getStatisticDataController()));
        viewNavigator.addView(MENU_PATIENTS_VIEW_NAME, new PatientView(dataControllerManager.getPatientDataController()));
        viewNavigator.addView(MENU_RECIPES_VIEW_NAME, new RecipeView(dataControllerManager.getRecipeDataController(),
                dataControllerManager.getDoctorDataController(), dataControllerManager.getPatientDataController()));
    }

    private void initMenuBar() {
        menuBar = new MenuBar();
        menuBar.setSizeFull();
        menuBar.setResponsive(true);
        menuBar.addItem(MENU_HOME_NAME, selectedItem -> {
            viewNavigator.navigateTo(MENU_HOME_VIEW_NAME);
        });
        menuBar.addItem(MENU_DOCTORS_NAME, selectedItem -> {
            viewNavigator.navigateTo(MENU_DOCTORS_VIEW_NAME);
        });
        menuBar.addItem(MENU_PATIENTS_NAME, selectedItem -> {
            viewNavigator.navigateTo(MENU_PATIENTS_VIEW_NAME);
        });
        menuBar.addItem(MENU_RECIPES_NAME, selectedItem -> {
            viewNavigator.navigateTo(MENU_RECIPES_VIEW_NAME);
        });
        header.addComponent(menuBar);
    }

    @Override
    protected void init(VaadinRequest request) {
        this.getPage().setTitle(PAGE_TITLE);

        initHeader();
        initShowLayout();
        initMainLayout();
        initViewDisplay();
        initViewNavigator();
        initMenuBar();

        setContent(mainLayout);
    }
}