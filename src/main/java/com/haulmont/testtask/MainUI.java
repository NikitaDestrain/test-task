package com.haulmont.testtask;

import com.haulmont.testtask.database.hibernate.DAOManagerHibernateImpl;
import com.haulmont.testtask.database.hibernate.DoctorDAOHibernateImpl;
import com.haulmont.testtask.domain.dto.DoctorDTO;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        try {
            for (DoctorDTO doctorDTO : DAOManagerHibernateImpl.getInstance().getDoctorDao().getAll()){
                layout.addComponent(new Label(doctorDTO.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setContent(layout);
    }
}