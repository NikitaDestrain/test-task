package com.haulmont.testtask.view.sub;

import com.haulmont.testtask.controller.implementation.DataControllerManagerImpl;
import com.haulmont.testtask.controller.interfaces.DoctorDataController;
import com.haulmont.testtask.controller.interfaces.PatientDataController;
import com.haulmont.testtask.controller.interfaces.RecipeDataController;
import com.haulmont.testtask.domain.auxiliary.Priority;
import com.haulmont.testtask.domain.dto.DoctorDTO;
import com.haulmont.testtask.domain.dto.PatientDTO;
import com.haulmont.testtask.domain.dto.RecipeDTO;
import com.haulmont.testtask.exception.controller.DataControllerReadingException;
import com.haulmont.testtask.exception.controller.DataControllerRemovingException;
import com.haulmont.testtask.exception.view.RefreshTableException;
import com.haulmont.testtask.view.sub.modal.manipulation.RecipeModalWindow;
import com.haulmont.testtask.view.utils.DoctorToStringConverter;
import com.haulmont.testtask.view.utils.PatientToStringConverter;
import com.haulmont.testtask.view.utils.PriorityToStringConverter;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import static com.haulmont.testtask.view.sub.NotificationMessageConstants.*;

public class RecipeView extends VerticalLayout implements View {

    public static final String TABLE_DESCRIPTION_COLUMN = "description";
    public static final String TABLE_PATIENT_COLUMN = "patient";
    public static final String TABLE_DOCTOR_COLUMN = "doctor";
    public static final String TABLE_CREATION_DATE_COLUMN = "creationDate";
    public static final String TABLE_EXPIRATION_DATE_COLUMN = "expirationDate";
    public static final String TABLE_PRIORITY_COLUMN = "priority";

    private static final String TABLE_DESCRIPTION_HEADER = "Description";
    private static final String TABLE_PATIENT_HEADER = "Patient";
    private static final String TABLE_DOCTOR_HEADER = "Doctor";
    private static final String TABLE_CREATION_DATE_HEADER = "Creation Date";
    private static final String TABLE_EXPIRATION_DATE_HEADER = "Expiration Date";
    private static final String TABLE_PRIORITY_HEADER = "Priority";
    private static final String TABLE_FOOTER = "Total: ";

    private static final String ADD_BUTTON_TEXT = "Add";
    private static final String EDIT_BUTTON_TEXT = "Edit";
    private static final String DELETE_BUTTON_TEXT = "Delete";

    private Table recipeTable;
    private MenuBar menuBar;
    private MenuBar.MenuItem addItem;
    private MenuBar.MenuItem editItem;
    private MenuBar.MenuItem deleteItem;

    private RecipeDataController recipeDataController;
    private PatientDataController patientDataController;
    private DoctorDataController doctorDataController;

    public RecipeView(RecipeDataController recipeDataController, DoctorDataController doctorDataController,
                      PatientDataController patientDataController) {
        this.recipeDataController = recipeDataController;
        this.patientDataController = patientDataController;
        this.doctorDataController = doctorDataController;
        initMenuBar();
        initTable();
        createFrame();
    }

    private void initMenuBar() {
        menuBar = new MenuBar();
        addItem = menuBar.addItem(ADD_BUTTON_TEXT, new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                RecipeModalWindow rMW = new RecipeModalWindow(
                        recipeDataController,
                        patientDataController,
                        doctorDataController
                );
                rMW.addCloseListener(closeEvent -> {
                    try {
                        refreshTable();
                    } catch (RefreshTableException e) {
                        System.out.println(e.getMessage());
                        Notification.show(DEFAULT_ERROR_MESSAGE, Notification.Type.ERROR_MESSAGE);
                    }
                });
                getUI().addWindow(rMW);
            }
        });
        editItem = menuBar.addItem(EDIT_BUTTON_TEXT, new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                Object tableValue = recipeTable.getValue();
                if (tableValue != null) {
                    RecipeModalWindow rMW = new RecipeModalWindow(
                            (Long) tableValue,
                            recipeTable.getItem(tableValue),
                            recipeDataController,
                            patientDataController,
                            doctorDataController);
                    rMW.addCloseListener(closeEvent -> {
                        try {
                            refreshTable();
                        } catch (RefreshTableException e) {
                            Notification.show(DEFAULT_ERROR_MESSAGE, Notification.Type.ERROR_MESSAGE);
                        }
                    });
                    getUI().addWindow(rMW);
                }
            }
        });
        deleteItem = menuBar.addItem(DELETE_BUTTON_TEXT, new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                Object tableValue = recipeTable.getValue();
                if (tableValue != null) {
                    try {
                        recipeDataController.remove((Long) tableValue);
                    } catch (DataControllerRemovingException e) {
                        Notification.show(DEFAULT_ERROR_MESSAGE_WITH_TRY_AGAIN_SUGGESTION, Notification.Type.ERROR_MESSAGE);
                    }
                }
                try {
                    refreshTable();
                } catch (RefreshTableException e) {
                    Notification.show(DEFAULT_ERROR_MESSAGE, Notification.Type.ERROR_MESSAGE);
                }
            }
        });
    }

    private void createFrame() {
        setMargin(true);
        addComponents(menuBar, recipeTable);
        setComponentAlignment(menuBar, Alignment.MIDDLE_CENTER);
    }

    private void initTable() {
        recipeTable = new Table();
        recipeTable.addContainerProperty(
                TABLE_DESCRIPTION_COLUMN,
                String.class,
                null,
                TABLE_DESCRIPTION_HEADER,
                null,
                Table.Align.CENTER
        );
        recipeTable.addContainerProperty(
                TABLE_PATIENT_COLUMN,
                PatientDTO.class,
                null,
                TABLE_PATIENT_HEADER,
                null,
                Table.Align.CENTER
        );
        recipeTable.addContainerProperty(
                TABLE_DOCTOR_COLUMN,
                DoctorDTO.class,
                null,
                TABLE_DOCTOR_HEADER,
                null,
                Table.Align.CENTER
        );
        recipeTable.addContainerProperty(
                TABLE_CREATION_DATE_COLUMN,
                Date.class,
                null,
                TABLE_CREATION_DATE_HEADER,
                null,
                Table.Align.CENTER
        );
        recipeTable.addContainerProperty(
                TABLE_EXPIRATION_DATE_COLUMN,
                Date.class,
                null,
                TABLE_EXPIRATION_DATE_HEADER,
                null,
                Table.Align.CENTER
        );
        recipeTable.addContainerProperty(
                TABLE_PRIORITY_COLUMN,
                Priority.class,
                null,
                TABLE_PRIORITY_HEADER,
                null,
                Table.Align.CENTER
        );

        recipeTable.setConverter(
                TABLE_CREATION_DATE_COLUMN,
                new StringToDateConverter() {
                    @Override
                    public DateFormat getFormat(Locale locale) {
                        return new SimpleDateFormat("dd.MM.yyyy");
                    }
                }
        );
        recipeTable.setConverter(
                TABLE_EXPIRATION_DATE_COLUMN,
                new StringToDateConverter() {
                    @Override
                    public DateFormat getFormat(Locale locale) {
                        return new SimpleDateFormat("dd.MM.yyyy");
                    }
                }
        );

        recipeTable.setConverter(TABLE_DOCTOR_COLUMN, new DoctorToStringConverter());
        recipeTable.setConverter(TABLE_PATIENT_COLUMN, new PatientToStringConverter());
        recipeTable.setConverter(TABLE_PRIORITY_COLUMN, new PriorityToStringConverter());

        recipeTable.addItemClickListener(event -> {
            if (event.getItem() != null) {
                enableButtons();
            } else {
                disableButtons();
            }
        });

        recipeTable.setSelectable(true);
        recipeTable.setImmediate(true);
        recipeTable.setNullSelectionAllowed(false);
        recipeTable.setSizeFull();
        recipeTable.setPageLength(recipeTable.size());
        recipeTable.setFooterVisible(true);
    }

    private void refreshTable() throws RefreshTableException {
        recipeTable.removeAllItems();
        try {
            List<RecipeDTO> recipes = recipeDataController.getAll();
            for (RecipeDTO recipe : recipes) {
                recipeTable.addItem(new Object[]{
                        recipe.getDescription(),
                        recipe.getPatient(),
                        recipe.getDoctor(),
                        recipe.getCreationDate(),
                        recipe.getExpirationDate(),
                        recipe.getPriority()
                }, recipe.getId());
            }
            recipeTable.setColumnFooter(TABLE_DESCRIPTION_COLUMN, TABLE_FOOTER + recipes.size());
        } catch (DataControllerReadingException e) {
            throw new RefreshTableException(e.getMessage());
        }
        disableButtons();
    }

    private void enableButtons() {
        editItem.setEnabled(true);
        deleteItem.setEnabled(true);
    }

    private void disableButtons() {
        editItem.setEnabled(false);
        deleteItem.setEnabled(false);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        try {
            refreshTable();
        } catch (RefreshTableException e) {
            Notification.show(DEFAULT_ERROR_MESSAGE, Notification.Type.ERROR_MESSAGE);
        }
    }
}