package com.haulmont.testtask.view.sub.modal.manipulation;

import com.haulmont.testtask.controller.interfaces.DoctorDataController;
import com.haulmont.testtask.domain.dto.DoctorDTO;
import com.haulmont.testtask.exception.controller.DataControllerCreationException;
import com.haulmont.testtask.exception.controller.DataControllerUpdatingException;
import com.haulmont.testtask.factory.dto.DoctorDTOFactory;
import com.haulmont.testtask.view.sub.DoctorView;
import com.haulmont.testtask.view.sub.interfaces.ModalWindow;

import com.haulmont.testtask.view.sub.interfaces.ModalWindowConstants;
import com.vaadin.data.Item;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class DoctorModalWindow extends ModalWindow<DoctorDTO> {

    private TextField nameField;
    private TextField surnameField;
    private TextField patronymicField;
    private TextField specializationField;

    public DoctorModalWindow(DoctorDataController dataController) {
        super();
        setCaption("Add doctor");
        createForm();
        submitButton.addClickListener(clickEvent -> {
            if (isDataValid()) {
                DoctorDTO doctorDTO = convert();
                try {
                    dataController.create(doctorDTO);
                } catch (DataControllerCreationException e) {
                    Notification.show(
                            "Oops!!!\n\nSomething went wrong :(\n\nPlease, try again",
                            Notification.Type.ERROR_MESSAGE
                    );
                }
                close();
            }
        });
    }

    public DoctorModalWindow(Long id, Item item, DoctorDataController dataController) {
        super();
        setCaption("Edit doctor");
        createForm();
        nameField.setValue(String.valueOf(item.getItemProperty(DoctorView.TABLE_NAME_COLUMN).getValue()));
        surnameField.setValue(String.valueOf(item.getItemProperty(DoctorView.TABLE_SURNAME_COLUMN).getValue()));
        patronymicField.setValue(String.valueOf(item.getItemProperty(DoctorView.TABLE_PATRONYMIC_COLUMN).getValue()));
        specializationField.setValue(String.valueOf(item.getItemProperty(DoctorView.TABLE_SPECIALIZATION_COLUMN).getValue()));

        submitButton.addClickListener(clickEvent -> {
            if (isDataValid()) {
                try {
                    dataController.update(convert(id));
                } catch (DataControllerUpdatingException e) {
                    Notification.show(
                            "Oops!!!\n\nSomething went wrong :(\n\nPlease, try again",
                            Notification.Type.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    @Override
    protected void createForm() {
        nameField = new TextField("Name");
        nameField.setRequired(true);
        nameField.setWidth("100%");
        nameField.setRequiredError("Enter name");
        nameField.setMaxLength(30);
        nameField.setImmediate(true);
        nameField.addValidator(new RegexpValidator(
                ModalWindowConstants.LETTER_REGEX,
                false,
                "Name must contain only letters")
        );

        surnameField = new TextField("Surname");
        surnameField.setRequired(true);
        surnameField.setWidth("100%");
        surnameField.setRequiredError("Enter surname");
        surnameField.setMaxLength(30);
        surnameField.setImmediate(true);
        surnameField.addValidator(new RegexpValidator(
                ModalWindowConstants.LETTER_REGEX,
                false,
                "Surname must contain only letters")
        );

        patronymicField = new TextField("Patronymic");
        patronymicField.setRequired(false);
        patronymicField.setWidth("100%");
        patronymicField.setMaxLength(30);
        patronymicField.setImmediate(true);
        patronymicField.addValidator(new RegexpValidator(
                ModalWindowConstants.LETTER_REGEX,
                false,
                "Patronymic must contain only letters")
        );

        specializationField = new TextField("Specialization");
        specializationField.setRequired(true);
        specializationField.setWidth("100%");
        specializationField.setRequiredError("Enter Specialization");
        specializationField.setMaxLength(40);
        specializationField.setImmediate(true);
        specializationField.addValidator(new RegexpValidator(
                ModalWindowConstants.LETTER_REGEX,
                false,
                "Specialization must contain only letters")
        );

        formLayout.addComponents(nameField, surnameField, patronymicField, specializationField);
        setContent(mainLayout);
    }

    @Override
    protected DoctorDTO convert() {
        return DoctorDTOFactory.create(
                nameField.getValue(),
                surnameField.getValue(),
                patronymicField.getValue(),
                specializationField.getValue()
        );
    }

    @Override
    protected DoctorDTO convert(Long id) {
        return DoctorDTOFactory.create(
                id,
                nameField.getValue(),
                surnameField.getValue(),
                patronymicField.getValue(),
                specializationField.getValue()
        );
    }

    @Override
    protected boolean isDataValid() {
        try {
            nameField.validate();
            surnameField.validate();
            patronymicField.validate();
            specializationField.validate();
        } catch (Validator.InvalidValueException e) {
            Notification.show(
                    "Incorrect input data :(\n\nClick me and try again",
                    Notification.Type.ERROR_MESSAGE
            );
            return false;
        }
        return true;
    }
}