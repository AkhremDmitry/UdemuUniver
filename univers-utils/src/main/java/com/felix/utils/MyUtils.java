package com.felix.utils;

public enum MyUtils {

  MENU_STUDENT("STUDENTS"),
  MENU_UNIVERSITY("UNIVERSITY"),
  MENU_ADD_STUDENT("Add Student"),
  MENU_REMOVE_STUDENT("Remove Student"),
  MENU_OPERATIONS("Operations");

  private final String string;

  private MyUtils (String string){
    this.string = string;
  }

  public String getString(){
    return this.string;
  }

}
