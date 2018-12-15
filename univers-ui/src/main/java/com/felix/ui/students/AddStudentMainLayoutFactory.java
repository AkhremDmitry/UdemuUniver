package com.felix.ui.students;

import com.felix.model.entity.Student;
import com.felix.utils.Gender;
import com.felix.utils.StudentsStringUtils;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.*;

@org.springframework.stereotype.Component
public class AddStudentMainLayoutFactory {

    private class AddStudentMainLayout extends VerticalLayout{

        private TextField firstName;
        private TextField lastName;
        private TextField age;
        private ComboBox gender;
        private Button saveButton;
        private Button clearButton;

        private BeanFieldGroup<Student> fieldGroup;
        private Student student;


        public AddStudentMainLayout init() {

            fieldGroup = new BeanFieldGroup<Student>(Student.class);
            firstName = new TextField(StudentsStringUtils.FIRST_NAME.getString());
            lastName = new TextField(StudentsStringUtils.LAST_NAME.getString());
            age = new TextField(StudentsStringUtils.AGE.getString());
            gender = new ComboBox(StudentsStringUtils.GENDER.getString());

            saveButton = new Button(StudentsStringUtils.SAVE_BUTTON.getString());
            clearButton = new Button(StudentsStringUtils.CLEAR_BUTTON.getString());

            gender.addItem(Gender.MALE.getString());
            gender.addItem(Gender.FEMALE.getString());
            return this;
        }

        public AddStudentMainLayout layout() {
            return this;
        }
    }

    public Component createComponet() {
        return new AddStudentMainLayout().init().layout();
    }
}
