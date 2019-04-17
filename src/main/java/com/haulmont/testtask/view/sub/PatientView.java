package com.haulmont.testtask.view.sub;

import com.haulmont.testtask.controller.interfaces.PatientDataController;
import com.haulmont.testtask.domain.dto.PatientDTO;
import com.haulmont.testtask.exception.controller.DataControllerReadingException;
import com.haulmont.testtask.exception.controller.DataControllerRemovingException;
import com.haulmont.testtask.exception.view.RefreshTableException;
import com.haulmont.testtask.view.sub.modal.manipulation.PatientModalWindow;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.List;

public class PatientView extends VerticalLayout implements View {

    public static final String TABLE_NAME_COLUMN = "name";
    public static final String TABLE_SURNAME_COLUMN = "surname";
    public static final String TABLE_PATRONYMIC_COLUMN = "patronymic";
    public static final String TABLE_PHONE_NUMBER_COLUMN = "phoneNumber";

    private static final String TABLE_NAME_HEADER = "Name";
    private static final String TABLE_SURNAME_HEADER = "Surname";
    private static final String TABLE_PATRONYMIC_HEADER = "Patronymic";
    private static final String TABLE_PHONE_NUMBER_HEADER = "Phone number";

    private static final String ADD_BUTTON_TEXT = "Add";
    private static final String EDIT_BUTTON_TEXT = "Edit";
    private static final String DELETE_BUTTON_TEXT = "Delete";

    private Table patientTable;
    private MenuBar menuBar;
    private MenuBar.MenuItem addItem;
    private MenuBar.MenuItem editItem;
    private MenuBar.MenuItem deleteItem;

    private PatientDataController dataController;

    public PatientView(PatientDataController patientDataController) {
        this.dataController = patientDataController;
        initMenuBar();
        initTable();
        createFrame();
    }

    private void initMenuBar() {
        menuBar = new MenuBar();
        addItem = menuBar.addItem(ADD_BUTTON_TEXT, new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                PatientModalWindow pMW = new PatientModalWindow(dataController);
                pMW.addCloseListener(closeEvent -> {
                    try {
                        refreshTable();
                    } catch (RefreshTableException e) {
                        System.out.println(e.getMessage());
                        Notification.show(
                                "Oops!!!\n\nSomething went wrong :(\n\nPlease, reload page",
                                Notification.Type.ERROR_MESSAGE
                        );
                    }
                });
                getUI().addWindow(pMW);
            }
        });
        editItem = menuBar.addItem(EDIT_BUTTON_TEXT, new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                Object tableValue = patientTable.getValue();
                if (tableValue != null) {
                    PatientModalWindow pMW = new PatientModalWindow(
                            (Long) tableValue,
                            patientTable.getItem(tableValue),
                            dataController);
                    pMW.addCloseListener(closeEvent -> {
                        try {
                            refreshTable();
                        } catch (RefreshTableException e) {
                            Notification.show(
                                    "Oops!!!\n\nSomething went wrong :(\n\nPlease, reload page",
                                    Notification.Type.ERROR_MESSAGE
                            );
                        }
                    });
                    getUI().addWindow(pMW);
                }
            }
        });
        deleteItem = menuBar.addItem(DELETE_BUTTON_TEXT, new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                Object tableValue = patientTable.getValue();
                if (tableValue != null) {
                    try {
                        dataController.remove((Long) tableValue);
                    } catch (DataControllerRemovingException e) {
                        Notification.show(
                                "Oops!!!\n\nSomething went wrong :(" +
                                        "\n\nPlease, make sure the petient has no recipes" +
                                        "\n\nClick me and try again",
                                Notification.Type.ERROR_MESSAGE
                        );
                    }
                }
                try {
                    refreshTable();
                } catch (RefreshTableException e) {
                    Notification.show(
                            "Oops!!!\n\nSomething went wrong :(\n\nPlease, reload page",
                            Notification.Type.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    private void createFrame() {
        setMargin(true);
        addComponents(menuBar, patientTable);
        setComponentAlignment(menuBar, Alignment.MIDDLE_CENTER);
    }

    private void initTable() {
        patientTable = new Table();
        patientTable.addContainerProperty(
                TABLE_NAME_COLUMN,
                String.class,
                null,
                TABLE_NAME_HEADER,
                null,
                Table.Align.CENTER
        );
        patientTable.addContainerProperty(
                TABLE_SURNAME_COLUMN,
                String.class,
                null,
                TABLE_SURNAME_HEADER,
                null,
                Table.Align.CENTER
        );
        patientTable.addContainerProperty(
                TABLE_PATRONYMIC_COLUMN,
                String.class,
                null,
                TABLE_PATRONYMIC_HEADER,
                null,
                Table.Align.CENTER
        );
        patientTable.addContainerProperty(
                TABLE_PHONE_NUMBER_COLUMN,
                String.class,
                null,
                TABLE_PHONE_NUMBER_HEADER,
                null,
                Table.Align.CENTER
        );
        patientTable.setSelectable(true);
        patientTable.setImmediate(true);
        patientTable.setNullSelectionAllowed(false);
        patientTable.setSizeFull();
    }

    private void refreshTable() throws RefreshTableException {
        patientTable.removeAllItems();
        try {
            List<PatientDTO> patients = dataController.getAll();
            for (PatientDTO patient : patients) {
                patientTable.addItem(new Object[]{
                        patient.getName(),
                        patient.getSurname(),
                        patient.getPatronymic(),
                        patient.getPhoneNumber()
                }, patient.getId());
            }
        } catch (DataControllerReadingException e) {
            throw new RefreshTableException(e.getMessage());
        }

        patientTable.addItemClickListener(event -> {
            if (event.getItem() != null) {
                enableButtons();
            } else {
                disableButtons();
            }
        });
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
            e.printStackTrace();
        }
    }
}