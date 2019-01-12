package com.felix.ui.login;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

@org.springframework.stereotype.Component
public class LoginFormFactory {

    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    private class LoginForm{

        private VerticalLayout root;
        private Panel panel;
        private TextField username;
        private PasswordField passwordField;
        private Button loginButton;
        private Button signupButton;

        public LoginForm init(){
            root = new VerticalLayout();
            root.setMargin(true);
            root.setHeight("100%");

            panel = new Panel("Login");
            panel.setSizeUndefined();

            loginButton = new Button("Login");
            loginButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
            signupButton = new Button("Sugn Up");
            signupButton.setStyleName(ValoTheme.BUTTON_PRIMARY);

            username = new TextField("Username");
            passwordField = new PasswordField("Password");

            return this;
        }

        public Component layout(){

            root.addComponent(panel);
            root.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

            FormLayout loginLayout = new FormLayout();
            loginLayout.addComponent(username);
            loginLayout.addComponent(passwordField);

            loginLayout.addComponent(new HorizontalLayout(loginButton, signupButton));
            loginLayout.setSizeUndefined();
            loginLayout.setMargin(true);

            loginButton.addClickListener(new Button.ClickListener() {
                public void buttonClick(Button.ClickEvent clickEvent) {

                    try {

                        Authentication auth = new UsernamePasswordAuthenticationToken(username.getValue(), passwordField.getValue());
                        Authentication authenticated = daoAuthenticationProvider.authenticate(auth);
                        SecurityContextHolder.getContext().setAuthentication(authenticated);

                    } catch (AuthenticationException e) {
                        Notification.show("Error", "Login fall! Try again", Notification.Type.ERROR_MESSAGE);
                    }
                }
            });

            signupButton.addClickListener(new Button.ClickListener() {
                public void buttonClick(Button.ClickEvent clickEvent) {

                }
            });

            panel.setContent(loginLayout);

            return root;
        }

    }

    public Component createComponent(){
        return new LoginForm().init().layout();
    }
}
