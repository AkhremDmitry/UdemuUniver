package com.felix.ui.students;

import com.felix.model.entity.Student;
import com.felix.utils.Gender;
import com.felix.utils.NotificationMessages;
import com.felix.utils.StudentsStringUtils;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

@org.springframework.stereotype.Component
public class AddStudentMainLayoutFactory {

    private class AddStudentMainLayout extends VerticalLayout implements Button.ClickListener{

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

            saveButton.addClickListener(this);
            clearButton.addClickListener(this);

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

        public void buttonClick(ClickEvent event) {
            if (event.getSource() == this.saveButton){
              save();
            } else {
              clearField();
            }
        }

        private void save(){
          try {
            fieldGroup.commit();
          } catch (CommitException e) {
            Notification.show(NotificationMessages.STUDENT_SAVE_VALITATION_ERROR_TITLE.getString(),
                NotificationMessages.STUDENT_SAVE_VALITATION_ERROR_DESCRIPTION.getString(),
                Type.ERROR_MESSAGE);
            return;
          }
          clearField();

          System.out.println(student);
        }

        private void clearField() {
            firstNameTextField.setValue(null);
            lastName.setValue(null);
            age.setValue(null);
            gender.setValue(null);
        }
    }

    public Component createComponet() {
        return new AddStudentMainLayout().init().bind().layout();
    }
}
