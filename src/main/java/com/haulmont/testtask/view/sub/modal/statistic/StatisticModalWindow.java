package com.haulmont.testtask.view.sub.modal.statistic;

import com.haulmont.testtask.domain.auxiliary.DoctorStatistic;
import com.haulmont.testtask.domain.dto.StatisticDTO;
import com.haulmont.testtask.view.sub.modal.interfaces.ModalWindow;
import com.haulmont.testtask.view.utils.DoctorStatisticToFullNameConverter;
import com.haulmont.testtask.view.utils.DoctorStatisticToStatisticConverter;
import com.haulmont.testtask.view.utils.StatisticToStringConverter;
import com.haulmont.testtask.view.utils.UIHelper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import java.util.Map;

public class StatisticModalWindow extends ModalWindow<StatisticDTO> {

    private static final String STATISTIC_WINDOW_WIDTH = "80%";
    private static final String STATISTIC_WINDOW_HEIGHT = "80%";
    private static final String WINDOW_NAME = "Recipe Doctor Statistic";

    private static final String TABLE_DOCTOR_COLUMN = "doctor";
    private static final String TABLE_NORMAL_COLUMN = "normal";
    private static final String TABLE_CITO_COLUMN = "cito";
    private static final String TABLE_STATIM_COLUMN = "statim";
    private static final String TABLE_TOTAL_COLUMN = "total";

    private static final String TABLE_DOCTOR_HEADER = "Doctor";
    private static final String TABLE_NORMAL_HEADER = "Normal";
    private static final String TABLE_CITO_HEADER = "Cito";
    private static final String TABLE_STATIM_HEADER = "Statim";
    private static final String TABLE_TOTAL_HEADER = "Total";

    private static final String TAB_NAME = "Statistic information";

    private StatisticDTO statisticDTO;
    private VerticalLayout statisticLayout;
    private Table statisticTable;

    public StatisticModalWindow(StatisticDTO statisticDTO) {
        this.statisticDTO = statisticDTO;
        initTable();
        createForm();
        refreshTable();
    }

    @Override
    protected void createForm() {
        setCaption(WINDOW_NAME);
        statisticLayout = new VerticalLayout();
        Label tabName = new Label(TAB_NAME);
        tabName.setStyleName(UIHelper.TAB_NAME_STYLE);
        setWidth(STATISTIC_WINDOW_WIDTH);
        setHeight(STATISTIC_WINDOW_HEIGHT);
        submitButton.setVisible(false);
        cancelButton.setVisible(false);

        submitButton.addClickListener(clickEvent -> {
            close();
        });

        submitButton.setEnabled(true);
        statisticLayout.setMargin(true);
        statisticLayout.addComponents(tabName, statisticTable, submitButton);
        statisticLayout.setComponentAlignment(statisticTable, Alignment.MIDDLE_CENTER);
        statisticLayout.setComponentAlignment(submitButton, Alignment.BOTTOM_CENTER);
        statisticLayout.setComponentAlignment(tabName, Alignment.TOP_CENTER);
        setContent(statisticLayout);
    }

    private void initTable() {
        statisticTable = new Table();
        statisticTable.addContainerProperty(
                TABLE_DOCTOR_COLUMN,
                DoctorStatistic.class,
                null,
                TABLE_DOCTOR_HEADER,
                null,
                Table.Align.CENTER
        );
        statisticTable.addContainerProperty(
                TABLE_NORMAL_COLUMN,
                Integer.class,
                null,
                TABLE_NORMAL_HEADER,
                null,
                Table.Align.CENTER
        );
        statisticTable.addContainerProperty(
                TABLE_CITO_COLUMN,
                Integer.class,
                null,
                TABLE_CITO_HEADER,
                null,
                Table.Align.CENTER
        );
        statisticTable.addContainerProperty(
                TABLE_STATIM_COLUMN,
                Integer.class,
                null,
                TABLE_STATIM_HEADER,
                null,
                Table.Align.CENTER
        );
        statisticTable.addContainerProperty(
                TABLE_TOTAL_COLUMN,
                DoctorStatistic.class,
                null,
                TABLE_TOTAL_HEADER,
                null,
                Table.Align.CENTER
        );

        statisticTable.setConverter(TABLE_DOCTOR_COLUMN, new DoctorStatisticToFullNameConverter());
        statisticTable.setConverter(TABLE_TOTAL_COLUMN, new DoctorStatisticToStatisticConverter());
        statisticTable.setSizeFull();
        statisticTable.setFooterVisible(true);
    }

    private void refreshTable() {
        statisticTable.removeAllItems();
        Map<Long, DoctorStatistic> doctorStatisticMap = statisticDTO.getDoctorStatisticMap();
        for (Long key : doctorStatisticMap.keySet()) {
            DoctorStatistic doctorStatistic = doctorStatisticMap.get(key);
            statisticTable.addItem(new Object[]{
                    doctorStatistic,
                    doctorStatistic.getNormalPriorityRecipeCount(),
                    doctorStatistic.getCitoPriorityRecipeCount(),
                    doctorStatistic.getStatimPriorityRecipeCount(),
                    doctorStatistic
            }, key);
        }
        statisticTable.setColumnFooter(TABLE_DOCTOR_COLUMN, TABLE_TOTAL_HEADER);
        statisticTable.setColumnFooter(
                TABLE_TOTAL_COLUMN,
                new StatisticToStringConverter().convertToPresentation(statisticDTO, String.class, null)
        );
    }

    @Override
    protected StatisticDTO convert() {
        return null;
    }

    @Override
    protected StatisticDTO convert(Long id) {
        return null;
    }

    @Override
    protected boolean isDataValid() {
        return true;
    }
}
