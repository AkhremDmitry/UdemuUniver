package com.felix.ui.universities;

import com.felix.ui.commons.UniversMainUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = UniversityLayoutFactory.NAME, ui=UniversMainUI.class)
public class UniversityLayoutFactory extends VerticalLayout implements View, UniversitySavedListener {

    public static final String NAME = "operations";

    private TabSheet tabSheet;

    @Autowired
    private AddUniversityLayoutFactory addUniversityLayoutFactory;

    @Autowired
    private ShowUniveritiesLayoutFactory showUniveritiesLayoutFactory;

    private void addLayout() {
        setMargin(true);

        tabSheet = new TabSheet();
        tabSheet.setWidth("100%");

        Component addUniversityTab = addUniversityLayoutFactory.createComponent(this);
        Component showAllUniversityTab = showUniveritiesLayoutFactory.createComponent();
        Component showStatisticsTab = new Label("Stats");

        tabSheet.addTab(addUniversityTab, "ADD UNIVERSITY");
        tabSheet.addTab(showAllUniversityTab, "SHOW ALL UNIVERSITY");
        tabSheet.addTab(showStatisticsTab, "STATISTICS");

        addComponent(tabSheet);
    }

    public void universitySaved() {
        showUniveritiesLayoutFactory.refreshTable();
    }

    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

        removeAllComponents();
        addLayout();
    }


}
