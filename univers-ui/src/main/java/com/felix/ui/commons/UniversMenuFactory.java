package com.felix.ui.commons;

import com.vaadin.ui.Component;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
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

            mainMenu.addItem("STUDENT");
            mainMenu.addItem("UNIVERSITY");

            mainMenu.expandItem("STUDENT");
            mainMenu.expandItem("UNIVERSITY");

            mainMenu.addItem("Add student");
            mainMenu.addItem("Remove student");
            mainMenu.setChildrenAllowed("Add student", false);
            mainMenu.setChildrenAllowed("Remove student", false);
            mainMenu.setParent("Add student", "STUDENT");
            mainMenu.setParent("Remove student", "STUDENT");

            mainMenu.addItem("Operations");
            mainMenu.setChildrenAllowed("Operations", false);
            mainMenu.setParent("Operations", "UNIVERSITY");

            addComponent(mainMenu);
            return this;
        }
    }
    public Component createComponent() {
        return new UniversMenu().init().layout();
    }
}
