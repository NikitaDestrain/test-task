package com.haulmont.testtask.view.sub;

import com.haulmont.testtask.controller.interfaces.PatientDataController;
import com.haulmont.testtask.controller.interfaces.RecipeDataController;
import com.haulmont.testtask.domain.dto.PatientDTO;
import com.haulmont.testtask.domain.dto.RecipeDTO;
import com.haulmont.testtask.exception.controller.DataControllerReadingException;
import com.haulmont.testtask.exception.controller.DataControllerRemovingException;
import com.haulmont.testtask.exception.view.RefreshTableException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.List;

public class RecipeView extends VerticalLayout implements View {

    public static final String TABLE_DESCRIPTION_COLUMN = "description";
    public static final String TABLE_PATIENT_COLUMN = "patient";
    public static final String TABLE_DOCTOR_COLUMN = "doctor";
    public static final String TABLE_CREATION_DATE_COLUMN = "creationDate";
    public static final String TABLE_EXPIRATION_DATE_COLUMN = "expirationDate";
    public static final String TABLE_PRIORITY_COLUMN = "priority";

    public static final String TABLE_DESCRIPTION_HEADER = "Description";
    public static final String TABLE_PATIENT_HEADER = "Patient";
    public static final String TABLE_DOCTOR_HEADER = "Doctor";
    public static final String TABLE_CREATION_DATE_HEADER = "Creation Date";
    public static final String TABLE_EXPIRATION_DATE_HEADER = "Expiration Date";
    public static final String TABLE_PRIORITY_HEADER = "Priority";

    private Table recipeTable;
    private MenuBar menuBar;
    private MenuBar.MenuItem addItem;
    private MenuBar.MenuItem editItem;
    private MenuBar.MenuItem deleteItem;

    private RecipeDataController dataController;

    public RecipeView(RecipeDataController recipeDataController) {
        this.dataController = recipeDataController;
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
                /*Object tableValue = recipeTable.getValue();
                if (tableValue != null) {
                    DoctorModalWindow dMW = new DoctorModalWindow(
                            (Long) tableValue,
                            recipeTable.getItem(tableValue),
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
                Object tableValue = recipeTable.getValue();
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
                String.class,
                null,
                TABLE_PATIENT_HEADER,
                null,
                Table.Align.CENTER
        );
        recipeTable.addContainerProperty(
                TABLE_DOCTOR_COLUMN,
                String.class,
                null,
                TABLE_DOCTOR_HEADER,
                null,
                Table.Align.CENTER
        );
        recipeTable.addContainerProperty(
                TABLE_CREATION_DATE_COLUMN,
                String.class,
                null,
                TABLE_CREATION_DATE_HEADER,
                null,
                Table.Align.CENTER
        );
        recipeTable.addContainerProperty(
                TABLE_EXPIRATION_DATE_COLUMN,
                String.class,
                null,
                TABLE_EXPIRATION_DATE_HEADER,
                null,
                Table.Align.CENTER
        );
        recipeTable.addContainerProperty(
                TABLE_PRIORITY_COLUMN,
                String.class,
                null,
                TABLE_PRIORITY_HEADER,
                null,
                Table.Align.CENTER
        );
        recipeTable.setSelectable(true);
        recipeTable.setImmediate(true);
        recipeTable.setNullSelectionAllowed(false);
        recipeTable.setSizeFull();
    }

    private void refreshTable() throws RefreshTableException {
        recipeTable.removeAllItems();
        try {
            List<RecipeDTO> recipes = dataController.getAll();
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
        } catch (DataControllerReadingException e) {
            throw new RefreshTableException(e.getMessage());
        }

        recipeTable.addItemClickListener(event -> {
            if (event.getItem() != null) {
                enableButtons();
            } else {
                disableButtons();
            }
        });

        // fixme open too much windows
        recipeTable.addItemClickListener(event -> {
            /*if (event.isDoubleClick()) {
                Object tableValue = recipeTable.getValue();
                if (tableValue != null) {
                    DoctorModalWindow dMW = new DoctorModalWindow(
                            (Long) tableValue,
                            recipeTable.getItem(tableValue),
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
