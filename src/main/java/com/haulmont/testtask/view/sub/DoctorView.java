package com.haulmont.testtask.view.sub;

import com.haulmont.testtask.controller.interfaces.DoctorDataController;
import com.haulmont.testtask.domain.dto.DoctorDTO;
import com.haulmont.testtask.exception.controller.DataControllerReadingException;
import com.haulmont.testtask.exception.controller.DataControllerRemovingException;
import com.haulmont.testtask.exception.view.RefreshTableException;
import com.haulmont.testtask.view.sub.modal.manipulation.DoctorModalWindow;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

@Theme(ValoTheme.THEME_NAME)
public class DoctorView extends VerticalLayout implements View {

    public static final String TABLE_NAME_COLUMN = "name";
    public static final String TABLE_SURNAME_COLUMN = "surname";
    public static final String TABLE_PATRONYMIC_COLUMN = "patronymic";
    public static final String TABLE_SPECIALIZATION_COLUMN = "specialization";

    public static final String TABLE_NAME_HEADER = "Name";
    public static final String TABLE_SURNAME_HEADER = "Surname";
    public static final String TABLE_PATRONYMIC_HEADER = "Patronymic";
    public static final String TABLE_SPECIALIZATION_HEADER = "Specialization";

    private Table doctorTable;
    private MenuBar menuBar;
    private MenuBar.MenuItem addItem;
    private MenuBar.MenuItem editItem;
    private MenuBar.MenuItem deleteItem;
    private MenuBar.MenuItem statisticItem;

    private DoctorDataController dataController;

    public DoctorView(DoctorDataController doctorDataController) {
        this.dataController = doctorDataController;
        initMenuBar();
        initTable();
        createFrame();
    }

    private void initMenuBar() {
        menuBar = new MenuBar();
        addItem = menuBar.addItem("Add", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                DoctorModalWindow dMW = new DoctorModalWindow(dataController);
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
                getUI().addWindow(dMW);
            }
        });
        editItem = menuBar.addItem("Edit", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                Object tableValue = doctorTable.getValue();
                if (tableValue != null) {
                    DoctorModalWindow dMW = new DoctorModalWindow(
                            (Long) tableValue,
                            doctorTable.getItem(tableValue),
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
            }
        });
        deleteItem = menuBar.addItem("Delete", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                Object tableValue = doctorTable.getValue();
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
        statisticItem = menuBar.addItem("Show Statistic", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {

            }
        });
    }

    private void createFrame() {
        setMargin(true);
        addComponents(menuBar, doctorTable);
        setComponentAlignment(menuBar, Alignment.MIDDLE_CENTER);
    }

    private void initTable() {
        doctorTable = new Table();
        doctorTable.addContainerProperty(
                TABLE_NAME_COLUMN,
                String.class,
                null,
                TABLE_NAME_HEADER,
                null,
                Table.Align.CENTER
        );
        doctorTable.addContainerProperty(
                TABLE_SURNAME_COLUMN,
                String.class,
                null,
                TABLE_SURNAME_HEADER,
                null,
                Table.Align.CENTER
        );
        doctorTable.addContainerProperty(
                TABLE_PATRONYMIC_COLUMN,
                String.class,
                null,
                TABLE_PATRONYMIC_HEADER,
                null,
                Table.Align.CENTER
        );
        doctorTable.addContainerProperty(
                TABLE_SPECIALIZATION_COLUMN,
                String.class,
                null,
                TABLE_SPECIALIZATION_HEADER,
                null,
                Table.Align.CENTER
        );
        doctorTable.setSelectable(true);
        doctorTable.setImmediate(true);
        doctorTable.setNullSelectionAllowed(false);
        doctorTable.setSizeFull();
    }

    private void refreshTable() throws RefreshTableException {
        doctorTable.removeAllItems();
        try {
            List<DoctorDTO> doctors = dataController.getAll();
            for (DoctorDTO doctor : doctors) {
                doctorTable.addItem(new Object[]{
                        doctor.getName(),
                        doctor.getSurname(),
                        doctor.getPatronymic(),
                        doctor.getSpecialization()
                }, doctor.getId());
            }
        } catch (DataControllerReadingException e) {
            throw new RefreshTableException(e.getMessage());
        }

        doctorTable.addItemClickListener(event -> {
            if (event.getItem() != null) {
                enableButtons();
            } else {
                disableButtons();
            }
        });

        // fixme open too much windows
        doctorTable.addItemClickListener(event -> {
            if (event.isDoubleClick()) {
                Object tableValue = doctorTable.getValue();
                if (tableValue != null) {
                    DoctorModalWindow dMW = new DoctorModalWindow(
                            (Long) tableValue,
                            doctorTable.getItem(tableValue),
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
            }
        });
        disableButtons();
    }

    private void enableButtons() {
        editItem.setEnabled(true);
        deleteItem.setEnabled(true);
        statisticItem.setEnabled(true);
    }

    private void disableButtons() {
        editItem.setEnabled(false);
        deleteItem.setEnabled(false);
        statisticItem.setEnabled(false);
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