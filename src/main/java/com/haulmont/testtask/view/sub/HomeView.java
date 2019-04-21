package com.haulmont.testtask.view.sub;

import com.haulmont.testtask.view.utils.UIHelper;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class HomeView extends VerticalLayout implements View {

    private static final String HEADER_TEXT = "Welcome to Hospital Recipe System";
    private static final String MAIN_TEXT = "This application is designed to manipulate data on recipes, patients and doctors.";
    private static final String ENJOY_TEXT = "Have a nice day and enjoy using it!";
    private static final String IMG_SRC = "img/home.png";
    private static final String IMG_WIDTH = "20%";
    private static final String IMG_HEIGHT = "20%";

    public HomeView() {
        create();
    }

    private void create() {
        VerticalLayout mainLayout = new VerticalLayout();
        Label header = new Label(HEADER_TEXT);
        header.setStyleName(UIHelper.HOME_TAB_NAME_STYLE);
        Label mainText = new Label(MAIN_TEXT);
        mainText.setStyleName(UIHelper.HOME_TEXT_STYLE);
        Label enjoyText = new Label(ENJOY_TEXT);
        enjoyText.setStyleName(UIHelper.HOME_TEXT_STYLE);
        ThemeResource resource = new ThemeResource(IMG_SRC);
        Image image = new Image("", resource);
        image.setWidth(IMG_WIDTH);
        image.setHeight(IMG_HEIGHT);
        image.setStyleName(UIHelper.HOME_IMAGE_STYLE);
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.addComponents(header, mainText, enjoyText, image);
        mainLayout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
        addComponent(mainLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
