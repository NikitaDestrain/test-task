package com.haulmont.testtask;

import com.haulmont.testtask.database.hibernate.DAOManagerHibernateImpl;
import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.domain.Specialization;
import com.haulmont.testtask.factory.DoctorFactory;
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
            layout.addComponent(new Label(DAOManagerHibernateImpl.getInstance().getDoctorDao().read(new Long(0)).toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }


        setContent(layout);
    }
}