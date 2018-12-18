package com.felix.ui.students;

import com.felix.model.entity.Student;
import com.felix.utils.Gender;
import com.felix.utils.StudentsStringUtils;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@org.springframework.stereotype.Component
public class AddStudentMainLayoutFactory {

    private class AddStudentMainLayout extends VerticalLayout{

        @PropertyId("firstName")
        private TextField firstNameTextField; //if we use name different from Student field's name
        private TextField lastName;
        private TextField age;
        private ComboBox gender;
        private Button saveButton;
        private Button clearButton;

        private BeanFieldGroup<Student> fieldGroup;
        private Student student;


        public AddStudentMainLayout init() {

            fieldGroup = new BeanFieldGroup<Student>(Student.class);
            student = new Student();

            firstNameTextField = new TextField(StudentsStringUtils.FIRST_NAME.getString());
            lastName = new TextField(StudentsStringUtils.LAST_NAME.getString());
            age = new TextField(StudentsStringUtils.AGE.getString());
            gender = new ComboBox(StudentsStringUtils.GENDER.getString());

            saveButton = new Button(StudentsStringUtils.SAVE_BUTTON.getString());
            clearButton = new Button(StudentsStringUtils.CLEAR_BUTTON.getString());

            saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
            clearButton.setStyleName(ValoTheme.BUTTON_PRIMARY);

            gender.addItem(Gender.MALE.getString());
            gender.addItem(Gender.FEMALE.getString());

            firstNameTextField.setNullRepresentation("");
            lastName.setNullRepresentation("");
            age.setNullRepresentation("");

            return this;
        }

        public AddStudentMainLayout bind() {
            fieldGroup.bindMemberFields(this);
            fieldGroup.setItemDataSource(student);
            return this;
        }

        public Component layout() {
            setMargin(true);
            GridLayout gridLayout = new GridLayout(2,3);
            gridLayout.setSizeUndefined();
            gridLayout.setSpacing(true);

            gridLayout.addComponent(firstNameTextField, 0,0);
            gridLayout.addComponent(lastName, 1,0);

            gridLayout.addComponent(age, 0,1);
            gridLayout.addComponent(gender, 1,1);
            gridLayout.addComponent(new HorizontalLayout(saveButton, clearButton), 0,2);

            return gridLayout;
        }


    }

    public Component createComponet() {
        return new AddStudentMainLayout().init().bind().layout();
    }
}
