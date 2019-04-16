package com.haulmont.testtask.view.sub;

import com.haulmont.testtask.controller.interfaces.PatientDataController;
import com.haulmont.testtask.domain.dto.PatientDTO;
import com.haulmont.testtask.exception.controller.DataControllerReadingException;
import com.haulmont.testtask.exception.controller.DataControllerRemovingException;
import com.haulmont.testtask.exception.view.RefreshTableException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.List;

public class PatientView extends VerticalLayout implements View {

    public static final String TABLE_NAME_COLUMN = "name";
    public static final String TABLE_SURNAME_COLUMN = "surname";
    public static final String TABLE_PATRONYMIC_COLUMN = "patronymic";
    public static final String TABLE_PHONE_NUMBER_COLUMN = "phoneNumber";

    public static final String TABLE_NAME_HEADER = "Name";
    public static final String TABLE_SURNAME_HEADER = "Surname";
    public static final String TABLE_PATRONYMIC_HEADER = "Patronymic";
    public static final String TABLE_PHONE_NUMBER_HEADER = "Phone number";

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
        addItem = menuBar.addItem("Add", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                /*DoctorModalWindow dMW = new DoctorModalWindow(dataController);
                dMW.addCloseListener(closeEvent -> {
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
                getUI().addWindow(dMW);*/
            }
        });
        editItem = menuBar.addItem("Edit", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                /*Object tableValue = patientTable.getValue();
                if (tableValue != null) {
                    DoctorModalWindow dMW = new DoctorModalWindow(
                            (Long) tableValue,
                            patientTable.getItem(tableValue),
                            dataController);
                    dMW.addCloseListener(closeEvent -> {
                        try {
                            refreshTable();
                        } catch (RefreshTableException e) {
                            Notification.show(
                                    "Oops!!!\n\nSomething went wrong :(\n\nPlease, reload page",
                                    Notification.Type.ERROR_MESSAGE
                            );
                        }
                    });
                    getUI().addWindow(dMW);
                }*/
            }
        });
        deleteItem = menuBar.addItem("Delete", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                Object tableValue = patientTable.getValue();
                if (tableValue != null) {
                    try {
                        dataController.remove((Long) tableValue);
                    } catch (DataControllerRemovingException e) {
                        Notification.show(
                                "Oops!!!\n\nSomething went wrong :(" +
                                        "\n\nPlease, make sure the doctor has no recipes" +
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

        // fixme open too much windows
        patientTable.addItemClickListener(event -> {
            /*if (event.isDoubleClick()) {
                Object tableValue = patientTable.getValue();
                if (tableValue != null) {
                    DoctorModalWindow dMW = new DoctorModalWindow(
                            (Long) tableValue,
                            patientTable.getItem(tableValue),
                            dataController);
                    dMW.addCloseListener(closeEvent -> {
                        try {
                            refreshTable();
                        } catch (RefreshTableException e) {
                            Notification.show(
                                    "Oops!!!\n\nSomething went wrong :(\n\nPlease, reload page",
                                    Notification.Type.ERROR_MESSAGE
                            );
                        }
                    });
                    getUI().addWindow(dMW);
                }
            }*/
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