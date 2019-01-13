package com.felix.ui.commons;

import com.felix.navigator.UniversNavigator;
import com.felix.utils.MyUtils;
import com.vaadin.data.Property;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.security.core.context.SecurityContextHolder;

@org.springframework.stereotype.Component
public class UniversMenuFactory implements UIComponentBuilder{

    private class UniversMenu extends VerticalLayout implements Property.ValueChangeListener {

        private Tree mainMenu;

        public UniversMenu init(){
            mainMenu = new Tree();
            mainMenu.addValueChangeListener(this);
            return this;
        }

        public UniversMenu layout(){
            setWidth("100%");
            setHeightUndefined();

            mainMenu.addItem(MyUtils.MENU_STUDENT.getString());
            mainMenu.addItem(MyUtils.MENU_UNIVERSITY.getString());
            mainMenu.addItem("LOGOUT");

            mainMenu.expandItem(MyUtils.MENU_STUDENT.getString());
            mainMenu.expandItem(MyUtils.MENU_UNIVERSITY.getString());
            mainMenu.expandItem("LOGOUT");

            mainMenu.addItem(MyUtils.MENU_ADD_STUDENT.getString());
            mainMenu.addItem(MyUtils.MENU_REMOVE_STUDENT.getString());
            mainMenu.setChildrenAllowed(MyUtils.MENU_ADD_STUDENT.getString(), false);
            mainMenu.setChildrenAllowed(MyUtils.MENU_REMOVE_STUDENT.getString(), false);
            mainMenu.setParent(MyUtils.MENU_ADD_STUDENT.getString(), MyUtils.MENU_STUDENT.getString());
            mainMenu.setParent(MyUtils.MENU_REMOVE_STUDENT.getString(), MyUtils.MENU_STUDENT.getString());

            mainMenu.addItem("Logout");
            mainMenu.setChildrenAllowed("Logout", false);
            mainMenu.setParent("Logout", "LOGOUT");

            mainMenu.addItem(MyUtils.MENU_OPERATIONS.getString());
            mainMenu.setChildrenAllowed(MyUtils.MENU_OPERATIONS.getString(), false);
            mainMenu.setParent(MyUtils.MENU_OPERATIONS.getString(), MyUtils.MENU_UNIVERSITY.getString());

            addComponent(mainMenu);
            return this;
        }

        public void valueChange(Property.ValueChangeEvent event) {
            String selectedItemPath = (String) event.getProperty().getValue();
            if (selectedItemPath == null || selectedItemPath.equals("LOGOUT")
                    || selectedItemPath.equals(MyUtils.MENU_STUDENT.getString())
                    || selectedItemPath.equals(MyUtils.MENU_UNIVERSITY.getString())){
                return;
            }

            if (selectedItemPath.equals("Logout")){
                SecurityContextHolder.clearContext();
                UI.getCurrent().getPage().setLocation("/login");
                return;
            }

            String path = selectedItemPath.toLowerCase().replaceAll("\\s+", "");
            UniversNavigator.navigate(path);
        }
    }
    public Component createComponent() {
        return new UniversMenu().init().layout();
    }
}
