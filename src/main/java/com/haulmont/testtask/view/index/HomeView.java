package com.haulmont.testtask.view.index;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class HomeView extends HorizontalLayout implements View {
    public HomeView() {
        create();
    }

    private void create() {
        addComponent(new Label("home"));

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
