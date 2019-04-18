package com.haulmont.testtask.view.sub.modal.statistic;

import com.haulmont.testtask.domain.dto.DoctorStatisticDTO;
import com.haulmont.testtask.view.sub.interfaces.ModalWindow;
import com.vaadin.server.PaintException;
import com.vaadin.server.PaintTarget;

public class DoctorStatisticModalWindow extends ModalWindow<DoctorStatisticDTO> {

    private static final String STATISTIC_WINDOW_WIDTH = "80%";
    private static final String STATISTIC_WINDOW_HEIGHT = "80%";

    public DoctorStatisticModalWindow() {
        createForm();
    }

    @Override
    protected void createForm() {
        setScrollLeft(1);
        setWidth(STATISTIC_WINDOW_WIDTH);
        setHeight(STATISTIC_WINDOW_HEIGHT);
        submitButton.setEnabled(true);
        cancelButton.setVisible(false);
    }

    @Override
    protected DoctorStatisticDTO convert() {
        return null;
    }

    @Override
    protected DoctorStatisticDTO convert(Long id) {
        return null;
    }

    @Override
    protected boolean isDataValid() {
        return true;
    }
}
