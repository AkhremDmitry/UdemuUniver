package com.felix.navigator;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

public class UniversNavigator extends Navigator {

  public UniversNavigator(UI ui, ComponentContainer container){
    super(ui, container);
  }

  public static UniversNavigator getNavigator(){
    UI ui = UI.getCurrent();
    Navigator navigator = ui.getNavigator();
    return (UniversNavigator) navigator;
  }

  public static void navigate(String path){
    try{
      UniversNavigator.getNavigator().navigateTo(path);
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public void navigateTo(String viewNAme) {
    super.navigateTo(Strings.nullToEmpty(viewNAme));
  }
}
