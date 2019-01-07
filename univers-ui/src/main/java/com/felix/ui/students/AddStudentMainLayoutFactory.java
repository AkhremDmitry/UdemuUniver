package com.felix.ui.students;

import com.felix.model.entity.Student;
import com.felix.model.entity.University;
import com.felix.service.student.AddStudentService;
import com.felix.service.university.ShowAllUniversitiesService;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Component
public class AddStudentMainLayoutFactory {

    private class AddStudentMainLayout extends VerticalLayout implements Button.ClickListener{

        @PropertyId("firstName")
        private TextField firstNameTextField; //if we use name different from Student field's name
        private TextField lastName;
        private TextField age;
        private ComboBox gender;
        private ComboBox university;
        private Button saveButton;
        private Button clearButton;

        private BeanFieldGroup<Student> fieldGroup;
        private Student student;

        private StudentSavedListener studentSavedListener;

        public AddStudentMainLayout (StudentSavedListener studentSavedListener){
            this.studentSavedListener = studentSavedListener;
        }

        public AddStudentMainLayout init() {

            fieldGroup = new BeanFieldGroup<Student>(Student.class);
            student = new Student();

            firstNameTextField = new TextField(StudentsStringUtils.FIRST_NAME.getString());
            lastName = new TextField(StudentsStringUtils.LAST_NAME.getString());
            age = new TextField(StudentsStringUtils.AGE.getString());
            gender = new ComboBox(StudentsStringUtils.GENDER.getString());

            saveButton = new Button(StudentsStringUtils.SAVE_BUTTON.getString());
            clearButton = new Button(StudentsStringUtils.CLEAR_BUTTON.getString());

            university = new ComboBox(StudentsStringUtils.UNIVERSITY.getString());
            university.setWidth("100%");

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
            GridLayout gridLayout = new GridLayout(2,4);
            gridLayout.setSizeUndefined();
            gridLayout.setSpacing(true);

            gridLayout.addComponent(firstNameTextField, 0,0);
            gridLayout.addComponent(lastName, 1,0);

            gridLayout.addComponent(age, 0,1);
            gridLayout.addComponent(gender, 1,1);
            gridLayout.addComponent(university, 0, 2, 1, 2);
            gridLayout.addComponent(new HorizontalLayout(saveButton, clearButton), 0,3);

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

            if (!isSaveOperationValid()) {
                Notification.show(NotificationMessages.STUDENT_SAVE_INVALID_TITLE.getString(),
                        NotificationMessages.STUDENT_SAVE_INVALID_DESCRIPTION.getString(),
                        Type.ERROR_MESSAGE);
                return;
            }
          try {
            fieldGroup.commit();
          } catch (CommitException e) {
            Notification.show(NotificationMessages.STUDENT_SAVE_VALITATION_ERROR_TITLE.getString(),
                NotificationMessages.STUDENT_SAVE_VALITATION_ERROR_DESCRIPTION.getString(),
                Type.ERROR_MESSAGE);
            return;
          }

          addStudentService.saveStudent(student);
          studentSavedListener.studentSaved();
          clearField();
            Notification.show(NotificationMessages.STUDENT_SAVE_SUCCESS_TITLE.getString(),
                    NotificationMessages.STUDENT_SAVE_SUCCESS_DESCRIPTION.getString(),
                    Type.WARNING_MESSAGE);

        }

        private void clearField() {
            firstNameTextField.setValue(null);
            lastName.setValue(null);
            age.setValue(null);
            gender.setValue(null);
            university.setValue(null);
        }

        private boolean isSaveOperationValid(){
            return showAllUniversitiesService.getAllUniversities().size() != 0;
        }

        public AddStudentMainLayout load(){

            List<University> universities = showAllUniversitiesService.getAllUniversities();
            university.addItems(universities);
            return this;
        }
    }

    @Autowired
    ShowAllUniversitiesService showAllUniversitiesService;

    @Autowired
    private AddStudentService addStudentService;

    public Component createComponet(StudentSavedListener studentSavedListener) {
        return new AddStudentMainLayout(studentSavedListener).init().load().bind().layout();
    }
}
