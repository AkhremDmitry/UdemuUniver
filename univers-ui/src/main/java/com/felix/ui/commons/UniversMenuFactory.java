package com.felix.ui.commons;

import com.felix.utils.MyUtils;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
@SpringView
public class UniversMenuFactory implements UIComponentBuilder{

    private class UniversMenu extends VerticalLayout{

        private Tree mainMenu;

        public UniversMenu init(){
            mainMenu = new Tree();
            return this;
        }

        public UniversMenu layout(){
            setWidth("100%");
            setHeightUndefined();

            mainMenu.addItem(MyUtils.MENU_STUDENT.getString());
            mainMenu.addItem(MyUtils.MENU_UNIVERSITY.getString());

            mainMenu.expandItem(MyUtils.MENU_STUDENT.getString());
            mainMenu.expandItem(MyUtils.MENU_UNIVERSITY.getString());

            mainMenu.addItem(MyUtils.MENU_ADD_STUDENT.getString());
            mainMenu.addItem(MyUtils.MENU_REMOVE_STUDENT.getString());
            mainMenu.setChildrenAllowed(MyUtils.MENU_ADD_STUDENT.getString(), false);
            mainMenu.setChildrenAllowed(MyUtils.MENU_REMOVE_STUDENT.getString(), false);
            mainMenu.setParent(MyUtils.MENU_ADD_STUDENT.getString(), MyUtils.MENU_STUDENT.getString());
            mainMenu.setParent(MyUtils.MENU_REMOVE_STUDENT.getString(), MyUtils.MENU_STUDENT.getString());

            mainMenu.addItem(MyUtils.MENU_OPERATIONS.getString());
            mainMenu.setChildrenAllowed(MyUtils.MENU_OPERATIONS.getString(), false);
            mainMenu.setParent(MyUtils.MENU_OPERATIONS.getString(), MyUtils.MENU_UNIVERSITY.getString());

            addComponent(mainMenu);
            return this;
        }
    }
    public Component createComponent() {
        return new UniversMenu().init().layout();
    }
}
