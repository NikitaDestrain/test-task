package com.haulmont.testtask.view.sub.modal.manipulation;

import com.haulmont.testtask.controller.interfaces.PatientDataController;
import com.haulmont.testtask.domain.dto.PatientDTO;
import com.haulmont.testtask.exception.controller.DataControllerCreationException;
import com.haulmont.testtask.exception.controller.DataControllerUpdatingException;
import com.haulmont.testtask.factory.dto.PatientDTOFactory;
import com.haulmont.testtask.view.sub.PatientView;
import com.haulmont.testtask.view.sub.modal.interfaces.ModalWindow;
import com.haulmont.testtask.view.sub.modal.interfaces.ModalWindowConstants;
import com.vaadin.data.Item;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import static com.haulmont.testtask.view.utils.NotificationMessageConstants.DEFAULT_ERROR_MESSAGE_WITH_TRY_AGAIN_SUGGESTION;
import static com.haulmont.testtask.view.utils.NotificationMessageConstants.INCORRECT_INPUT_DATA_MESSAGE;

public class PatientModalWindow extends ModalWindow<PatientDTO> {

    private static final String EDIT_PATIENT = "Edit patient";
    private static final String ADD_PATIENT = "Add patient";

    private TextField nameField;
    private TextField surnameField;
    private TextField patronymicField;
    private TextField phoneNumberField;

    public PatientModalWindow(PatientDataController dataController) {
        super();
        setCaption(ADD_PATIENT);
        createForm();
        submitButton.addClickListener(clickEvent -> {
            if (isDataValid()) {
                PatientDTO patientDTO = convert();
                try {
                    dataController.create(patientDTO);
                } catch (DataControllerCreationException e) {
                    Notification.show(DEFAULT_ERROR_MESSAGE_WITH_TRY_AGAIN_SUGGESTION, Notification.Type.ERROR_MESSAGE);
                }
                close();
            }
        });
    }

    public PatientModalWindow(Long id, Item item, PatientDataController dataController) {
        super();
        setCaption(EDIT_PATIENT);
        createForm();
        nameField.setValue(String.valueOf(item.getItemProperty(PatientView.TABLE_NAME_COLUMN).getValue()));
        surnameField.setValue(String.valueOf(item.getItemProperty(PatientView.TABLE_SURNAME_COLUMN).getValue()));
        patronymicField.setValue(String.valueOf(item.getItemProperty(PatientView.TABLE_PATRONYMIC_COLUMN).getValue()));
        phoneNumberField.setValue(String.valueOf(item.getItemProperty(PatientView.TABLE_PHONE_NUMBER_COLUMN).getValue()));

        submitButton.addClickListener(clickEvent -> {
            if (isDataValid()) {
                try {
                    dataController.update(convert(id));
                } catch (DataControllerUpdatingException e) {
                    Notification.show(DEFAULT_ERROR_MESSAGE_WITH_TRY_AGAIN_SUGGESTION, Notification.Type.ERROR_MESSAGE);
                }
            }
            close();
        });
    }

    @Override
    protected void createForm() {
        nameField = new TextField("Name");
        nameField.setRequired(true);
        nameField.setWidth(FIELD_WIDTH);
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
        surnameField.setWidth(FIELD_WIDTH);
        surnameField.setRequiredError("Enter surname");
        surnameField.setMaxLength(30);
        surnameField.setImmediate(true);
        surnameField.addValidator(new RegexpValidator(
                ModalWindowConstants.LETTER_REGEX,
                false,
                "Surname must contain only letters")
        );

        patronymicField = new TextField("Patronymic (optional)");
        patronymicField.setRequired(false);
        patronymicField.setWidth(FIELD_WIDTH);
        patronymicField.setMaxLength(30);
        patronymicField.setImmediate(true);
        patronymicField.addValidator(new RegexpValidator(
                ModalWindowConstants.LETTER_REGEX,
                false,
                "Patronymic must contain only letters")
        );

        phoneNumberField = new TextField("Phone nubmer");
        phoneNumberField.setRequired(true);
        phoneNumberField.setWidth(FIELD_WIDTH);
        phoneNumberField.setRequiredError("Enter Phone Number");
        phoneNumberField.setMaxLength(15);
        phoneNumberField.setImmediate(true);
        phoneNumberField.addValidator(new RegexpValidator(
                ModalWindowConstants.NUMBER_REGEX,
                false,
                "Phone number must contain only numbers")
        );

        nameField.addBlurListener(event -> {
            if (nameField.isValid() && surnameField.isValid() && patronymicField.isValid() && phoneNumberField.isValid()) {
                submitButton.setEnabled(true);
            } else {
                submitButton.setEnabled(false);
            }
        });

        surnameField.addBlurListener(event -> {
            if (nameField.isValid() && surnameField.isValid() && patronymicField.isValid() && phoneNumberField.isValid()) {
                submitButton.setEnabled(true);
            } else {
                submitButton.setEnabled(false);
            }
        });

        patronymicField.addBlurListener(event -> {
            if (nameField.isValid() && surnameField.isValid() && patronymicField.isValid() && phoneNumberField.isValid()) {
                submitButton.setEnabled(true);
            } else {
                submitButton.setEnabled(false);
            }
        });

        phoneNumberField.addBlurListener(event -> {
            if (nameField.isValid() && surnameField.isValid() && patronymicField.isValid() && phoneNumberField.isValid()) {
                submitButton.setEnabled(true);
            } else {
                submitButton.setEnabled(false);
            }
        });

        formLayout.addComponents(nameField, surnameField, patronymicField, phoneNumberField);
        setContent(mainLayout);
    }

    @Override
    protected PatientDTO convert() {
        return PatientDTOFactory.create(
                nameField.getValue(),
                surnameField.getValue(),
                patronymicField.getValue(),
                phoneNumberField.getValue()
        );
    }

    @Override
    protected PatientDTO convert(Long id) {
        return PatientDTOFactory.create(
                id,
                nameField.getValue(),
                surnameField.getValue(),
                patronymicField.getValue(),
                phoneNumberField.getValue()
        );
    }

    @Override
    protected boolean isDataValid() {
        try {
            nameField.validate();
            surnameField.validate();
            patronymicField.validate();
            phoneNumberField.validate();
        } catch (Validator.InvalidValueException e) {
            Notification.show(INCORRECT_INPUT_DATA_MESSAGE, Notification.Type.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}