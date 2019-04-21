package com.haulmont.testtask.view.sub;

import com.haulmont.testtask.controller.interfaces.DoctorDataController;
import com.haulmont.testtask.controller.interfaces.StatisticDataController;
import com.haulmont.testtask.domain.dto.DoctorDTO;
import com.haulmont.testtask.exception.controller.DataControllerReadingException;
import com.haulmont.testtask.exception.controller.DataControllerRemovingException;
import com.haulmont.testtask.exception.controller.DataControllerStatisticCreationException;
import com.haulmont.testtask.exception.view.RefreshTableException;
import com.haulmont.testtask.view.sub.modal.manipulation.DoctorModalWindow;
import com.haulmont.testtask.view.sub.modal.statistic.StatisticModalWindow;
import com.haulmont.testtask.view.utils.UIHelper;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

import static com.haulmont.testtask.view.utils.NotificationMessageConstants.DEFAULT_ERROR_MESSAGE;
import static com.haulmont.testtask.view.utils.NotificationMessageConstants.DOCTOR_RECIPE_CONSTRAINT_MESSAGE;

@Theme(ValoTheme.THEME_NAME)
public class DoctorView extends VerticalLayout implements View {

    public static final String TABLE_NAME_COLUMN = "name";
    public static final String TABLE_SURNAME_COLUMN = "surname";
    public static final String TABLE_PATRONYMIC_COLUMN = "patronymic";
    public static final String TABLE_SPECIALIZATION_COLUMN = "specialization";

    private static final String TABLE_NAME_HEADER = "Name";
    private static final String TABLE_SURNAME_HEADER = "Surname";
    private static final String TABLE_PATRONYMIC_HEADER = "Patronymic";
    private static final String TABLE_SPECIALIZATION_HEADER = "Specialization";
    private static final String TABLE_FOOTER = "Total: ";

    private static final String ADD_BUTTON_TEXT = "Add";
    private static final String EDIT_BUTTON_TEXT = "Edit";
    private static final String DELETE_BUTTON_TEXT = "Delete";
    private static final String STATISTIC_BUTTON_TEXT = "Show Statistic";

    private static final String TAB_NAME = "Doctor information";

    private Table doctorTable;
    private MenuBar menuBar;
    private MenuBar.MenuItem addItem;
    private MenuBar.MenuItem editItem;
    private MenuBar.MenuItem deleteItem;
    private MenuBar.MenuItem statisticItem;

    private DoctorDataController dataController;
    private StatisticDataController statisticDataController;

    public DoctorView(DoctorDataController doctorDataController, StatisticDataController statisticDataController) {
        this.dataController = doctorDataController;
        this.statisticDataController = statisticDataController;
        initMenuBar();
        initTable();
        createFrame();
    }

    private void initMenuBar() {
        menuBar = new MenuBar();
        addItem = menuBar.addItem(ADD_BUTTON_TEXT, selectedItem -> {
            DoctorModalWindow dMW = new DoctorModalWindow(dataController);
            dMW.addCloseListener(closeEvent -> {
                try {
                    refreshTable();
                } catch (RefreshTableException e) {
                    System.out.println(e.getMessage());
                    Notification.show(DEFAULT_ERROR_MESSAGE, Notification.Type.ERROR_MESSAGE);
                }
            });
            getUI().addWindow(dMW);
        });
        editItem = menuBar.addItem(EDIT_BUTTON_TEXT, selectedItem -> {
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
                        Notification.show(DEFAULT_ERROR_MESSAGE, Notification.Type.ERROR_MESSAGE);
                    }
                });
                getUI().addWindow(dMW);
            }
        });
        deleteItem = menuBar.addItem(DELETE_BUTTON_TEXT, selectedItem -> {
            Object tableValue = doctorTable.getValue();
            if (tableValue != null) {
                try {
                    dataController.remove((Long) tableValue);
                } catch (DataControllerRemovingException e) {
                    Notification.show(DOCTOR_RECIPE_CONSTRAINT_MESSAGE, Notification.Type.ERROR_MESSAGE);
                }
            }
            try {
                refreshTable();
            } catch (RefreshTableException e) {
                Notification.show(DEFAULT_ERROR_MESSAGE, Notification.Type.ERROR_MESSAGE);
            }
        });
        statisticItem = menuBar.addItem(STATISTIC_BUTTON_TEXT, selectedItem -> {
            try {
                StatisticModalWindow sMW = new StatisticModalWindow(statisticDataController.processStatistic());
                sMW.addCloseListener(closeEvent -> {
                    try {
                        refreshTable();
                    } catch (RefreshTableException e) {
                        Notification.show(DEFAULT_ERROR_MESSAGE, Notification.Type.ERROR_MESSAGE);
                    }
                });
                getUI().addWindow(sMW);
            } catch (DataControllerStatisticCreationException e) {
                Notification.show(DEFAULT_ERROR_MESSAGE, Notification.Type.ERROR_MESSAGE);
            }
        });
    }

    private void createFrame() {
        setMargin(true);
        Label tabName = new Label(TAB_NAME);
        tabName.setStyleName(UIHelper.TAB_NAME_STYLE);
        addComponents(tabName, menuBar, doctorTable);
        setComponentAlignment(menuBar, Alignment.MIDDLE_CENTER);
        setComponentAlignment(tabName, Alignment.TOP_CENTER);
        setSpacing(true);

        addLayoutClickListener(layoutClickEvent -> {
            Object selectedValue = doctorTable.getValue();
            if (selectedValue != null &&
                    !(layoutClickEvent.getClickedComponent() instanceof Table) &&
                    !(layoutClickEvent.getClickedComponent() instanceof MenuBar)) {
                doctorTable.unselect(selectedValue);
                disableButtons();
            }
        });
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

        doctorTable.addItemClickListener(event -> {
            if (event.getItem() != null) {
                enableButtons();
            } else {
                disableButtons();
            }
        });

        doctorTable.setSelectable(true);
        doctorTable.setImmediate(true);
        doctorTable.setNullSelectionAllowed(false);
        doctorTable.setSizeFull();
        doctorTable.setFooterVisible(true);
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
            if (doctors.size() != 0) {
                statisticItem.setEnabled(true);
            } else {
                statisticItem.setEnabled(false);
            }
            doctorTable.setColumnFooter(TABLE_NAME_COLUMN, TABLE_FOOTER + doctors.size());
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