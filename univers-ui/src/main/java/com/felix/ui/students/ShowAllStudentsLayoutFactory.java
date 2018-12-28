package com.felix.ui.students;

import com.felix.model.entity.Student;
import com.felix.service.showallstudents.ShowAllStudentsService;
import com.felix.ui.commons.UIComponentBuilder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Component
public class ShowAllStudentsLayoutFactory implements UIComponentBuilder {

    private List<Student> students;
    private BeanItemContainer<Student> container;

    private class ShowAllStudentsLayout extends VerticalLayout{

        private Grid studentsTable;

        public ShowAllStudentsLayout init(){

            setMargin(true);
            container = new BeanItemContainer<Student>(Student.class, students);

            studentsTable = new Grid(container);
            studentsTable.setColumnOrder("firstName", "lastName", "age", "gender");
            studentsTable.removeColumn("id");
            studentsTable.setImmediate(true);

            return this;
        }

        public ShowAllStudentsLayout load(){

            students = showAllStudentsService.getAllStudents();
            return this;
        }

        public ShowAllStudentsLayout layout(){

            addComponent(studentsTable);
            return this;
        }
    }

    public void refreshTable() {
        students = showAllStudentsService.getAllStudents();
        container.removeAllItems();
        container.addAll(students);
    }

    @Autowired
    private ShowAllStudentsService showAllStudentsService;

    public Component createComponent() {
        return new ShowAllStudentsLayout().load().init().layout();
    }
}
