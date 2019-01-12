package com.felix.ui.login;

import com.felix.service.security.RegisterUserService;
import com.felix.ui.commons.UIComponentBuilder;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component
public class SignupFormFactory implements UIComponentBuilder {

    @Autowired
    private RegisterUserService registerUserService;

    private class SignupForm{

        private VerticalLayout root;
        private Panel panel;
        private TextField username;
        private PasswordField passwordField;
        private PasswordField passwordAgainField;
        private Button saveButton;

        public SignupForm init(){

            root = new VerticalLayout();
            root.setMargin(true);
            root.setHeight("100%");

            panel = new Panel("Signup");
            panel.setSizeUndefined();

            saveButton = new Button("Save");
            saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

            username = new TextField("Username");
            passwordField = new PasswordField("Password");
            passwordAgainField = new PasswordField("PasswordAgain");

            saveButton.addClickListener(new Button.ClickListener() {
                public void buttonClick(Button.ClickEvent clickEvent) {

                    if( !passwordField.getValue().equals(passwordAgainField.getValue())){
                        new Notification("Error", "Passwords do not match", Notification.Type.ERROR_MESSAGE);
                        return;
                    }
                    registerUserService.save(username.getValue(), passwordField.getValue());
                    UI.getCurrent().getPage().setLocation("/login");
                }
            });

            return this;
        }

        public Component layout(){
            root.addComponent(panel);
            root.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

            FormLayout signupLayout = new FormLayout();
            signupLayout.addComponent(username);
            signupLayout.addComponent(passwordField);
            signupLayout.addComponent(passwordAgainField);

            signupLayout.addComponent(new HorizontalLayout(saveButton));
            signupLayout.setSizeUndefined();
            signupLayout.setMargin(true);

            return root;
        }
    }
    public Component createComponent() {
        return new SignupForm().init().layout();
    }
}
