package com.haulmont.testtask.view.sub.modal.manipulation;

import com.haulmont.testtask.controller.interfaces.DoctorDataController;
import com.haulmont.testtask.controller.interfaces.PatientDataController;
import com.haulmont.testtask.controller.interfaces.RecipeDataController;
import com.haulmont.testtask.domain.auxiliary.Priority;
import com.haulmont.testtask.domain.dto.DoctorDTO;
import com.haulmont.testtask.domain.dto.PatientDTO;
import com.haulmont.testtask.domain.dto.RecipeDTO;
import com.haulmont.testtask.exception.controller.DataControllerCreationException;
import com.haulmont.testtask.exception.controller.DataControllerReadingException;
import com.haulmont.testtask.exception.controller.DataControllerUpdatingException;
import com.haulmont.testtask.factory.dto.RecipeDTOFactory;
import com.haulmont.testtask.view.sub.RecipeView;
import com.haulmont.testtask.view.sub.interfaces.ModalWindow;
import com.haulmont.testtask.view.sub.interfaces.ModalWindowConstants;
import com.haulmont.testtask.view.utils.DoctorToStringConverter;
import com.haulmont.testtask.view.utils.PatientToStringConverter;
import com.haulmont.testtask.view.utils.PriorityToStringConverter;
import com.vaadin.data.Item;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import static com.haulmont.testtask.view.sub.NotificationMessageConstants.*;

public class RecipeModalWindow extends ModalWindow<RecipeDTO> {

    private static final String EDIT_PATIENT = "Edit recipe";
    private static final String ADD_PATIENT = "Add recipe";

    private static final String RECIPE_WINDOW_WIDTH = "70%";

    private TextArea descriptionField;
    private ComboBox patientField;
    private ComboBox doctorField;
    private DateField expirationDateField;
    private ComboBox priorityField;

    private PatientDataController patientDataController;
    private DoctorDataController doctorDataController;

    public RecipeModalWindow(RecipeDataController recipeDataController,
                             PatientDataController patientDataController,
                             DoctorDataController doctorDataController) {
        super();
        setCaption(ADD_PATIENT);
        createForm();
        submitButton.addClickListener(clickEvent -> {
            if (isDataValid()) {
                RecipeDTO recipeDTO = convert();
                try {
                    recipeDataController.create(recipeDTO);
                } catch (DataControllerCreationException e) {
                    Notification.show(DEFAULT_ERROR_MESSAGE_WITH_TRY_AGAIN_SUGGESTION, Notification.Type.ERROR_MESSAGE);
                }
                close();
            }
        });
        this.doctorDataController = doctorDataController;
        this.patientDataController = patientDataController;
    }

    public RecipeModalWindow(Long id,
                             Item item,
                             RecipeDataController recipeDataController,
                             PatientDataController patientDataController,
                             DoctorDataController doctorDataController) {
        super();
        setCaption(EDIT_PATIENT);
        createForm();
        descriptionField.setValue(String.valueOf(item.getItemProperty(RecipeView.TABLE_DESCRIPTION_COLUMN).getValue()));
        initPatientField(String.valueOf(item.getItemProperty(RecipeView.TABLE_PATIENT_COLUMN).getValue()));
        initDoctorField(String.valueOf(item.getItemProperty(RecipeView.TABLE_DOCTOR_COLUMN).getValue()));
        expirationDateField.setValue((Date) item.getItemProperty(RecipeView.TABLE_EXPIRATION_DATE_COLUMN).getValue());
        initPriorityField(String.valueOf(item.getItemProperty(RecipeView.TABLE_PRIORITY_COLUMN).getValue()));

        submitButton.addClickListener(clickEvent -> {
            if (isDataValid()) {
                try {
                    recipeDataController.update(convert(id));
                } catch (DataControllerUpdatingException e) {
                    Notification.show(DEFAULT_ERROR_MESSAGE_WITH_TRY_AGAIN_SUGGESTION, Notification.Type.ERROR_MESSAGE);
                }
            }
            close();
        });
        this.doctorDataController = doctorDataController;
        this.patientDataController = patientDataController;
    }

    @Override
    protected void createForm() {
        descriptionField = new TextArea("Description");
        descriptionField.setRequired(true);
        descriptionField.setWidth(FIELD_WIDTH);
        descriptionField.setRows(3);
        descriptionField.setWordwrap(true);
        descriptionField.setRequiredError("Enter description");
        descriptionField.setMaxLength(30);
        descriptionField.setImmediate(true);
        descriptionField.addValidator(new RegexpValidator(
                ModalWindowConstants.LETTER_REGEX_200_CHARS,
                false,
                "Description must contain only russian or english letters")
        );

        patientField = new ComboBox("Patient");
        patientField.setRequired(true);
        patientField.setWidth(FIELD_WIDTH);
        patientField.setRequiredError("Choose patient");
        patientField.setImmediate(true);
        patientField.addValidator(new NullValidator("Choose patient", false));
        patientField.setNullSelectionAllowed(false);
        patientField.setFilteringMode(FilteringMode.CONTAINS);

        doctorField = new ComboBox("Doctor");
        doctorField.setRequired(true);
        doctorField.setWidth(FIELD_WIDTH);
        doctorField.setRequiredError("Choose doctor");
        doctorField.setImmediate(true);
        doctorField.addValidator(new NullValidator("Choose doctor", false));
        doctorField.setNullSelectionAllowed(false);
        doctorField.setFilteringMode(FilteringMode.CONTAINS);

        expirationDateField = new DateField("Expiration date");
        expirationDateField.setRequired(true);
        expirationDateField.setWidth("100%");
        expirationDateField.setRequiredError("Укажите дату рождения");
        expirationDateField.setDateFormat("dd.MM.yyyy");
        expirationDateField.setValue(new Date(System.currentTimeMillis()));
        expirationDateField.setRangeStart(new Date(System.currentTimeMillis()));

        priorityField = new ComboBox("Priority");
        priorityField.setRequired(true);
        priorityField.setWidth(FIELD_WIDTH);
        priorityField.setRequiredError("Choose priority");
        priorityField.setImmediate(true);
        priorityField.addValidator(new NullValidator("Choose priority", false));
        priorityField.setNullSelectionAllowed(false);
        priorityField.setFilteringMode(FilteringMode.CONTAINS);

        descriptionField.addBlurListener(event -> {
            if (descriptionField.isValid() && patientField.isValid() &&
                    doctorField.isValid() && expirationDateField.isValid() && priorityField.isValid()) {
                submitButton.setEnabled(true);
            } else {
                submitButton.setEnabled(false);
            }
        });

        patientField.addBlurListener(event -> {
            if (descriptionField.isValid() && patientField.isValid() &&
                    doctorField.isValid() && expirationDateField.isValid() && priorityField.isValid()) {
                submitButton.setEnabled(true);
            } else {
                submitButton.setEnabled(false);
            }
        });

        doctorField.addBlurListener(event -> {
            if (descriptionField.isValid() && patientField.isValid() &&
                    doctorField.isValid() && expirationDateField.isValid() && priorityField.isValid()) {
                submitButton.setEnabled(true);
            } else {
                submitButton.setEnabled(false);
            }
        });

        expirationDateField.addBlurListener(event -> {
            if (descriptionField.isValid() && patientField.isValid() &&
                    doctorField.isValid() && expirationDateField.isValid() && priorityField.isValid()) {
                submitButton.setEnabled(true);
            } else {
                submitButton.setEnabled(false);
            }
        });

        priorityField.addBlurListener(event -> {
            if (descriptionField.isValid() && patientField.isValid() &&
                    doctorField.isValid() && expirationDateField.isValid() && priorityField.isValid()) {
                submitButton.setEnabled(true);
            } else {
                submitButton.setEnabled(false);
            }
        });

        formLayout.addComponents(descriptionField, patientField, doctorField, expirationDateField, priorityField);
        setContent(mainLayout);
        setWidth(RECIPE_WINDOW_WIDTH);
    }

    private void initPatientField(String currentPatient) {
        patientField.removeAllItems();
        List<PatientDTO> patients = new ArrayList<>();
        try {
            patients = patientDataController.getAll();
        } catch (DataControllerReadingException e) {
            Notification.show(DEFAULT_ERROR_MESSAGE, Notification.Type.ERROR_MESSAGE);
            close();
        }
        for (PatientDTO patient : patients) {
            patientField.addItem(patient);
            patientField.setItemCaption(
                    patient,
                    new PatientToStringConverter().convertToPresentation(patient, String.class, null)
            );
            if (currentPatient == null) {
                patientField.select(patient);
            } else if (currentPatient.equals(new PatientToStringConverter().convertToPresentation(
                    patient,
                    String.class, null))) {
                patientField.select(patient);
            }
        }
    }

    private void initPriorityField(String currentPriority) {
        priorityField.removeAllItems();
        for (Priority priority : Priority.values()) {
            priorityField.addItem(priority);
            priorityField.setItemCaption(
                    priority,
                    new PriorityToStringConverter().convertToPresentation(priority, String.class, null)
            );
            if (currentPriority == null) {
                priorityField.select(priority);
            } else if (currentPriority.equals(new PriorityToStringConverter().convertToPresentation(
                    priority,
                    String.class, null))) {
                priorityField.select(priority);
            }
        }
    }

    private void initDoctorField(String currentDoctor) {
        doctorField.removeAllItems();
        List<DoctorDTO> doctors = new ArrayList<>();
        try {
            doctors = doctorDataController.getAll();
        } catch (DataControllerReadingException e) {
            Notification.show(DEFAULT_ERROR_MESSAGE, Notification.Type.ERROR_MESSAGE);
            close();
        }
        for (DoctorDTO doctor : doctors) {
            doctorField.addItem(doctor);
            doctorField.setItemCaption(
                    doctor,
                    new DoctorToStringConverter().convertToPresentation(doctor, String.class, null)
            );
            if (currentDoctor == null) {
                patientField.select(doctor);
            } else if (currentDoctor.equals(new DoctorToStringConverter().convertToPresentation(
                    doctor,
                    String.class, null))) {
                doctorField.select(doctor);
            }
        }
    }


    @Override
    protected RecipeDTO convert() {
        return RecipeDTOFactory.create(
                descriptionField.getValue(),
                (PatientDTO) patientField.getValue(),
                (DoctorDTO) doctorField.getValue(),
                new Date(System.currentTimeMillis()),
                new Date(expirationDateField.getValue().getTime()),
                (Priority) priorityField.getValue()
        );
    }

    @Override
    protected RecipeDTO convert(Long id) {
        return RecipeDTOFactory.create(
                id,
                descriptionField.getValue(),
                (PatientDTO) patientField.getValue(),
                (DoctorDTO) doctorField.getValue(),
                new Date(System.currentTimeMillis()),
                new Date(expirationDateField.getValue().getTime()),
                (Priority) priorityField.getValue()
        );
    }

    @Override
    protected boolean isDataValid() {
        try {
            descriptionField.validate();
            patientField.validate();
            doctorField.validate();
            expirationDateField.validate();
            priorityField.validate();
        } catch (Validator.InvalidValueException e) {
            Notification.show(INCORRECT_INPUT_DATA_MESSAGE, Notification.Type.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}