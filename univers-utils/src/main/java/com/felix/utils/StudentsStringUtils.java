package com.felix.utils;

public enum StudentsStringUtils {
    MAIN_MENU("MAIN MENU"),
    SHOW_ALL_STUDENTS("SHOW ALL STUDENTS");

    private final String string;

    private StudentsStringUtils(String string){
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
