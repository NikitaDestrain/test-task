package com.haulmont.testtask.view.sub.interfaces;

import com.vaadin.ui.*;

public abstract class ModalWindow<T> extends Window {

    protected Button submitButton;
    protected Button cancelButton;
    protected HorizontalLayout buttonLayout;

    protected FormLayout formLayout;
    protected VerticalLayout mainLayout;

    public ModalWindow() {
        setModal(true);
        setWidth("35%");
        setHeight("70%");
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

        submitButton = new Button("Submit");
        cancelButton = new Button("Cancel");
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
