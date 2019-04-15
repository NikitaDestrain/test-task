package com.haulmont.testtask.view.ui;

import com.haulmont.testtask.view.index.HomeView;
import com.haulmont.testtask.view.sub.DoctorView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        this.getPage().setTitle("Home");
        Button homeButton = new Button("Home");
        Button doctorButton = new Button("Doctors");
        Button patientButton = new Button("Patients");
        Button recipeButton = new Button("Recipes");
        HorizontalLayout header = new HorizontalLayout();
        header.addStyleName("header-layout");
        header.setWidth("100%");
        header.setMargin(true);
        header.setSpacing(true);
        homeButton.addStyleName("button");
        doctorButton.addStyleName("button");
        patientButton.addStyleName("button");
        recipeButton.addStyleName("button");
        header.addComponents(homeButton, doctorButton, patientButton, recipeButton);
        VerticalLayout showLayout = new VerticalLayout();
        showLayout.setWidth("100%");
        showLayout.setHeight("100%");
        showLayout.addStyleName("view-layout");
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.addStyleName("main-layout");
        mainLayout.setHeight("100%");
        mainLayout.addComponents(header, showLayout);
        mainLayout.setExpandRatio(header, 1);
        mainLayout.setExpandRatio(showLayout, 9);

        ViewDisplay viewDisplay = new Navigator.ComponentContainerViewDisplay(showLayout);
        Navigator navigator = new Navigator(this, viewDisplay);
        navigator.addView("", new HomeView());
        navigator.addView("doctors", new DoctorView());

        homeButton.addClickListener(clickEvent -> navigator.navigateTo(""));
        doctorButton.addClickListener(clickEvent -> navigator.navigateTo("doctors"));
        setContent(mainLayout);
    }
}