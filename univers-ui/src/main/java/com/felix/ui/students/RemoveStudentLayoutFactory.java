package com.felix.ui.students;

import com.felix.model.entity.Student;
import com.felix.service.showallstudents.ShowAllStudentsService;
import com.felix.service.student.RemoveStudentService;
import com.felix.ui.commons.UniversMainUI;
import com.felix.utils.NotificationMessages;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.MultiSelectionModel;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringView(name = RemoveStudentLayoutFactory.NAME, ui = UniversMainUI.class)
public class RemoveStudentLayoutFactory extends VerticalLayout implements View, Button.ClickListener {

    public static final String NAME = "removestudent";
    private Grid removeStudentTable;
    private Button removeStudentButton;
    private List<Student> students;

    @Autowired
    private ShowAllStudentsService allStudentsService;

    @Autowired
    private RemoveStudentService removeStudentService;

    private void addLayout(){
        removeStudentButton = new Button(NotificationMessages.STUDENT_REMOVE_BUTTON.getString());

        setMargin(true);
        BeanItemContainer<Student> container = new BeanItemContainer<Student>(Student.class, students);

        removeStudentTable = new Grid(container);
        removeStudentTable.setColumnOrder("firstName", "lastName", "age", "gender");
        removeStudentTable.removeColumn("id");
        removeStudentTable.removeColumn("university");
        removeStudentTable.setImmediate(true);
        removeStudentTable.setSelectionMode(Grid.SelectionMode.MULTI);

        removeStudentButton.addClickListener(this);
        removeStudentButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

        addComponent(removeStudentTable);
        addComponent(removeStudentButton);
    }

    private void loadStudents(){
        students = allStudentsService.getAllStudents();
    }


    public void buttonClick(Button.ClickEvent clickEvent) {
        MultiSelectionModel selectionModel = (MultiSelectionModel)removeStudentTable.getSelectionModel();

        for(Object selectedItem : selectionModel.getSelectedRows()){
            Student student = (Student)selectedItem;
            removeStudentTable.getContainerDataSource().removeItem(student);
            removeStudentService.removeStudent(student);
        }

        Notification.show(NotificationMessages.STUDENT_REMOVE_SUCCESS_TITLE.getString(),
                NotificationMessages.STUDENT_REMOVE_SUCCESS_DESCRIPTION.getString(), Notification.Type.WARNING_MESSAGE);

        removeStudentTable.getSelectionModel().reset();
    }

    public void enter(ViewChangeEvent event) {
        if (removeStudentTable != null){
            return;
        }

        loadStudents();
        addLayout();
    }
}
