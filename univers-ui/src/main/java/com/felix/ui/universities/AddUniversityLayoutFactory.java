package com.felix.ui.universities;

import com.felix.model.entity.University;
import com.felix.service.university.AddUniversityService;
import com.felix.utils.NotificationMessages;
import com.felix.utils.UniversityStringUtils;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component
public class AddUniversityLayoutFactory {

    @Autowired
    private AddUniversityService addUniversityService;

    private class AddUniversitylayout extends VerticalLayout implements Button.ClickListener{

        private TextField universityName;
        private TextField universityCountry;
        private TextField universityCity;
        private Button saveButton;
        private University university;
        private BeanFieldGroup<University> fieldGroup;

        public void buttonClick(Button.ClickEvent clickEvent) {

            try {
                fieldGroup.commit();
            } catch (FieldGroup.CommitException e) {
                Notification.show(NotificationMessages.UNIVERSITY_SAVED_VALIDATION_ERROR_TITLE.getString(),
                        NotificationMessages.UNIVERSITY_SAVED_VALIDATION_ERROR_DESCRIPTION.getString(), Notification.Type.ERROR_MESSAGE);
                return;
            }

            clearFields();
            addUniversityService.addUniversity(university);
            Notification.show(NotificationMessages.UNIVERSITY_SAVE_SUCCESS_TITLE.getString(),
                    NotificationMessages.UNIVERSITY_SAVE_SUCCESS_DESCRIPTION.getString(), Notification.Type.WARNING_MESSAGE);

        }

        private void clearFields(){
            universityCountry.setValue(null);
            universityName.setValue(null);
            universityCity.setValue(null);
        }

        public AddUniversitylayout init() {

            university = new University();

            universityName = new TextField(UniversityStringUtils.UNIVERSITY_NAME.getString());
            universityCountry = new TextField(UniversityStringUtils.UNIVERSITY_COUNTRY.getString());
            universityCity = new TextField(UniversityStringUtils.UNIVERSITY_CITY.getString());

            saveButton = new Button(UniversityStringUtils.SAVE_BUTTON.getString(), this);
            saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

            universityName.setNullRepresentation("");
            universityCountry.setNullRepresentation("");
            universityCity.setNullRepresentation("");

            return this;
        }

        public AddUniversitylayout bind() {

            fieldGroup = new BeanFieldGroup<University>(University.class);
            fieldGroup.bindMemberFields(this);
            fieldGroup.setItemDataSource(university);

            return this;
        }

        public Component layout() {
            setWidth("100%");

            GridLayout grid = new GridLayout(1, 4);
            grid.setHeightUndefined();
            grid.setSpacing(true);

            grid.addComponent(universityName, 0, 0);
            grid.addComponent(universityCountry, 0, 1);
            grid.addComponent(universityCity, 0, 2);
            grid.addComponent(saveButton, 0, 3);
            return grid;
        }
    }

    public Component createComponent(){
        return new AddUniversitylayout().init().bind().layout();
    }
}
