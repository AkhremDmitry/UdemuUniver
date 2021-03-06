package com.felix.ui.students;

import com.felix.ui.commons.UniversMainUI;
import com.felix.utils.StudentsStringUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name=StudentLayoutFactory.NAME, ui=UniversMainUI.class)
public class StudentLayoutFactory extends VerticalLayout implements View, StudentSavedListener {

    public static final String NAME = "addstudent";

    @Autowired
    private AddStudentMainLayoutFactory mainLayoutFactory;

    @Autowired
    private ShowAllStudentsLayoutFactory showAllStudentsLayoutFactory;

    private TabSheet tabSheet;

    private void addLayout(){
        setMargin(true);
        tabSheet = new TabSheet();
        tabSheet.setWidth("100%");

        Component addStudentMainTab = mainLayoutFactory.createComponet(this);
        Component showStudentsTab = showAllStudentsLayoutFactory.createComponent();

        tabSheet.addTab(addStudentMainTab, StudentsStringUtils.MAIN_MENU.getString());
        tabSheet.addTab(showStudentsTab, StudentsStringUtils.SHOW_ALL_STUDENTS.getString());
        addComponent(tabSheet);
    }

    public void studentSaved() {
        showAllStudentsLayoutFactory.refreshTable();
    }

    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        removeAllComponents();
        addLayout();
    }
}
