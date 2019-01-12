package com.felix.ui.login;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = LoginUI.PATH)
@Theme("valo")
public class LoginUI extends UI {

    public static final String PATH = "/login";

    @Autowired
    private LoginFormFactory loginFormFactory;

    protected void init(VaadinRequest vaadinRequest) {
        setContent(loginFormFactory.createComponent());
    }
}
