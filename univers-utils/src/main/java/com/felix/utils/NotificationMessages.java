package com.felix.utils;

public enum NotificationMessages {

  STUDENT_SAVE_VALITATION_ERROR_TITLE("ERROR"),
  STUDENT_SAVE_VALITATION_ERROR_DESCRIPTION("Field must be filled!")
  ;

  private final String string;

  private NotificationMessages(String string){
    this.string = string;
  }

  public String getString() {
    return string;
  }
}
