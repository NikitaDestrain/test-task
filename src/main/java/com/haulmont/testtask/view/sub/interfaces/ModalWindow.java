package com.haulmont.testtask.view.sub.interfaces;

import com.vaadin.ui.*;

public abstract class ModalWindow<T> extends Window {

    private static final String WINDOW_WIDTH = "35%";
    private static final String WINDOW_HEIGHT = "40%";
    private static final String SUBMIT_BUTTON_TEXT = "Submit";
    private static final String CANCEL_BUTTON_TEXT = "Cancel";

    protected static final String FIELD_WIDTH = "100%";

    protected Button submitButton;
    protected Button cancelButton;
    protected HorizontalLayout buttonLayout;

    protected FormLayout formLayout;
    protected VerticalLayout mainLayout;

    public ModalWindow() {
        setModal(true);
        setWidth(WINDOW_WIDTH);
        setHeight(WINDOW_HEIGHT);
        setResizable(false);
        setDraggable(false);

        mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);

        formLayout = new FormLayout();
        formLayout.setSizeFull();
        formLayout.setMargin(false);
        formLayout.setSpacing(true);

        submitButton = new Button(SUBMIT_BUTTON_TEXT);
        submitButton.setEnabled(false);
        cancelButton = new Button(CANCEL_BUTTON_TEXT);
        cancelButton.addClickListener(clickEvent -> close());

        buttonLayout = new HorizontalLayout();
        buttonLayout.addComponents(submitButton, cancelButton);
        buttonLayout.setSpacing(true);

        mainLayout.addComponents(formLayout, buttonLayout);
        mainLayout.setComponentAlignment(buttonLayout, Alignment.BOTTOM_CENTER);
    }

    protected abstract void createForm();

    protected abstract T convert();

    protected abstract T convert(Long id);

    protected abstract boolean isDataValid();
}
