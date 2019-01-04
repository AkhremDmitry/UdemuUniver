package com.felix.utils;

public enum UniversityStringUtils {

    UNIVERSITY_NAME("Name"),
    UNIVERSITY_COUNTRY("Country"),
    UNIVERSITY_CITY("City"),


    SAVE_BUTTON("Save"),
    CLEAR_BUTTON("Clear");

    private final String string;

    private UniversityStringUtils(String string){
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
