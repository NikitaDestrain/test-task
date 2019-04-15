package com.haulmont.testtask.view.sub;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class DoctorView extends HorizontalLayout implements View {
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addComponent(new Label("Doctors"));
    }
}
