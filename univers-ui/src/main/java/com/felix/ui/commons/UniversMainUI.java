package com.felix.ui.commons;

import com.felix.navigator.UniversNavigator;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@SpringUI(path=UniversMainUI.NAME)
@Title("U n i v e r s")
@Theme("valo")
public class UniversMainUI extends UI {

    public static final String NAME = "/ui";

    private Panel changeTab = new Panel();

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private SpringViewProvider viewProvider;
    @Autowired
    private UniversMenuFactory universMenuFactory;
    @Autowired
    private UniversLogoLayoutFactory universLogoLayoutFactory;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        changeTab.setHeight("100%");

        VerticalLayout rootLayout = new VerticalLayout();
        rootLayout.setSizeFull();
        rootLayout.setMargin(true);

        Panel contentPanel = new Panel();
        contentPanel.setWidth("75%");
        contentPanel.setHeight("100%");

        Panel logoPanel = new Panel();
        logoPanel.setWidth("75%");
        logoPanel.setHeightUndefined();

        HorizontalLayout uiLayout = new HorizontalLayout();
        uiLayout.setSizeFull();
        uiLayout.setMargin(true);

        Component logo = universLogoLayoutFactory.createComponent();
        Component menu = universMenuFactory.createComponent();

        uiLayout.addComponent(menu);
        uiLayout.addComponent(changeTab);

        uiLayout.setComponentAlignment(changeTab, Alignment.TOP_CENTER);
        uiLayout.setComponentAlignment(menu, Alignment.TOP_CENTER);

        uiLayout.setExpandRatio(menu, 1);
        uiLayout.setExpandRatio(changeTab, 2);

        logoPanel.setContent(logo);
        contentPanel.setContent(uiLayout);

        rootLayout.addComponent(logoPanel);
        rootLayout.addComponent(contentPanel);
        rootLayout.setComponentAlignment(contentPanel, Alignment.MIDDLE_CENTER);
        rootLayout.setComponentAlignment(logoPanel, Alignment.TOP_CENTER);
        rootLayout.setExpandRatio(contentPanel, 1);

        initNavigator();

        setContent(rootLayout);
    }

    private void initNavigator() {
        UniversNavigator navigator = new UniversNavigator(this, changeTab);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(navigator); //add navigator to context
        navigator.addProvider(viewProvider);
    }
}
