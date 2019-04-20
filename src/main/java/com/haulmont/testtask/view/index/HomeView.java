package com.haulmont.testtask.view.index;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;

@Theme(ValoTheme.THEME_NAME)
public class HomeView extends VerticalLayout implements View {

    public HomeView() {
        create();
    }

    private void create() {
        Label text = new Label("Hello world!");
        text.setCaption("Information");
        addComponent(text);
        this.setComponentAlignment(text, Alignment.MIDDLE_CENTER);


        HorizontalLayout filterLayout = new HorizontalLayout();
        HorizontalLayout checkBoxLayout = new HorizontalLayout();
        TextField filterValue = new TextField();
        filterValue.focus();
        Label filterLabel = new Label("Filter");
        OptionGroup filterSelector = new OptionGroup();
        filterSelector.addItems("Description", "Priority", "Patient");
        Button discard = new Button("Discard");
        discard.addClickListener(clickEvent -> {
            filterValue.setValue("");
        });
        checkBoxLayout.addComponents(filterSelector, discard);
        filterLayout.addComponents(filterLabel, filterValue, checkBoxLayout);
        filterLayout.setComponentAlignment(filterLabel, Alignment.MIDDLE_LEFT);
        filterLayout.setSpacing(true);
        addComponent(filterLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
